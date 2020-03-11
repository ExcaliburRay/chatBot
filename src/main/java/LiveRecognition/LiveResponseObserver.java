package LiveRecognition;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.StreamingRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;

import java.util.ArrayList;
import java.util.List;

public class LiveResponseObserver implements ResponseObserver<StreamingRecognizeResponse> {
    private List<StreamingRecognizeResponse> responses = new ArrayList();

    public void onStart(StreamController controller) {}

    public void onResponse(StreamingRecognizeResponse response) {
        responses.add(response);
    }

    public void onComplete() {
    }

    public void onError(Throwable t) {
        System.out.println(t);
    }

    // return recognition result
    public String getResult(){
        return responses.get(1).getResultsList().get(0).getAlternativesList().get(0).getTranscript();
    }

    // detect the end of streaming
    public boolean isEnd(){
        if(responses.size()==2) return true;
        else return false;
    }
}
