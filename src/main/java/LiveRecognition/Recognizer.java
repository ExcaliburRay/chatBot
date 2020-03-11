package LiveRecognition;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.TargetDataLine;

public class Recognizer {

    public static final String credentials = "SpeechToText.json";

    public static void main(String... args) throws Exception {
        System.out.println(streamingMicRecognize());
    }

    // Perform microphone streaming recognition
    public static String streamingMicRecognize() throws Exception {
        ResponseObserver<StreamingRecognizeResponse> responseObserver = null;
        try {
            // Configure Google Cloud credentials
            FileInputStream credentialsStream = new FileInputStream(credentials);
            GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
            FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);
            SpeechSettings speechSettings =
                    SpeechSettings.newBuilder()
                            .setCredentialsProvider(credentialsProvider)
                            .build();

            // Instantiates a client
            SpeechClient client = SpeechClient.create(speechSettings);
            responseObserver = new LiveResponseObserver();

            ClientStream<StreamingRecognizeRequest> clientStream =
                    client.streamingRecognizeCallable().splitCall(responseObserver);

            // Configure recognition setting
            RecognitionConfig recognitionConfig =
                    RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                            .setSampleRateHertz(16000)
                            .build();
            StreamingRecognitionConfig streamingRecognitionConfig =
                    StreamingRecognitionConfig.newBuilder()
                            .setConfig(recognitionConfig)
                            .setSingleUtterance(true) // Stop recognition when a pause is detected
                            .build();

            StreamingRecognizeRequest request =
                    StreamingRecognizeRequest.newBuilder()
                            .setStreamingConfig(streamingRecognitionConfig)
                            .build(); // The first request in a streaming call has to be a config

            clientStream.send(request);
            // SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed: true,
            // bigEndian: false
            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
            DataLine.Info targetInfo =
                    new Info(
                            TargetDataLine.class,
                            audioFormat); // Set the system information to read from the microphone audio stream

            if (!AudioSystem.isLineSupported(targetInfo)) {
                System.out.println("Microphone not supported");
                System.exit(0);
            }

            // Target data line captures the audio stream the microphone produces.
            TargetDataLine targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();
            System.out.println("Start speaking");
            // Audio Input Stream
            AudioInputStream audio = new AudioInputStream(targetDataLine);

            while (true) {

                byte[] data = new byte[6400];
                audio.read(data);

                request =
                        StreamingRecognizeRequest.newBuilder()
                                .setAudioContent(ByteString.copyFrom(data))
                                .build();
                clientStream.send(request);
                // Return result when pause
                if(((LiveResponseObserver) responseObserver).isEnd()){
                    targetDataLine.stop();
                    targetDataLine.close();
                    break;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        responseObserver.onComplete();
        return ((LiveResponseObserver) responseObserver).getResult();
    }
    // [END speech_transcribe_streaming_mic]
}
