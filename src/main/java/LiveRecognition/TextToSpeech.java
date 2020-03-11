package LiveRecognition;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;

import java.io.*;


public class TextToSpeech{

    public static final String credential_path = "TextToSpeech.json";


    public static void toSpeech(String text){

        try {
            // Configure Google Cloud credentials
            FileInputStream credentialsStream = new FileInputStream(credential_path);
            GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
            FixedCredentialsProvider credentialsProvider = FixedCredentialsProvider.create(credentials);
            TextToSpeechSettings speechSettings =
                    TextToSpeechSettings.newBuilder()
                            .setCredentialsProvider(credentialsProvider)
                            .build();

            TextToSpeechClient textToSpeechClient = TextToSpeechClient.create(speechSettings);
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder()
                    .setText(text)
                    .build();

            // Build the voice request, select the language code ("en-US") and the ssml voice gender
            // ("neutral")
            VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                    .setLanguageCode("en-US")
                    .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                    .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig = AudioConfig.newBuilder()
                    .setAudioEncoding(AudioEncoding.MP3)
                    .build();

            // Perform the text-to-speech request on the text input with the selected voice parameters and
            // audio file type
            SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice,
                    audioConfig);

            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();

            // Write the response to the output file.
            try  {
                OutputStream out = new FileOutputStream("output.mp3");
                out.write(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.mp3\"");

            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        toSpeech("Hi Google. How can I help?");
    }
}