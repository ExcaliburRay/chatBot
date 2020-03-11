package mm;

import java.util.Scanner;

import Pandorabots.MagicParameters;
import Pandorabots.PandorabotsAPI;

/**
 *the BoxMade class contains methods to create a chat box and talk to user 
 * in response to their question
 * @author islane
 */
public class BoxMade {

    
    public String botName = "bot";
    PandorabotsAPI talker;
    private String response;

    /**
     *the createBot methods create an instance of PandorabotsAPI with ID and userKey
     * upload the local aiml file 
     */
    public void createBot() {
        MagicParameters parameter = new MagicParameters();
        System.out.print(parameter.getAppId());
        talker = new PandorabotsAPI(parameter.getHostName(), parameter.getAppId(), parameter.getUserKey(), parameter.isDebug());
        String response;
        try {
            //System.out.println("Creating Chating Box"+ botName);
            //response = talker.deleteBot(botName);
            //response = talker.createBot(botName);
            //System.out.println("Upload file aiml to " + botName);
            response = talker.uploadFile(botName, "receptionlife.aiml"); //load aiml file
            //System.out.println("Upload file properties to " + botName);
            response = talker.uploadFile(botName, "bot.properties");
            //System.out.println("Compiling Chating Box"+ botName);
            response = talker.compileBot(botName);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     *the talk method takes in user's question, implement the talk method in 
     * PandorabotsAPI to get response based on the question 
     * @param sentence
     * @return
     */
    public String talk(String sentence) {

        try {

            String question = sentence;
            response = talker.atalk(botName, question);
            while (response.equals("")) {
                response = talker.atalk(botName, question);
            }

        } catch (Exception e) {
            System.out.print(e.toString());
        }
        return response;
    }

}
