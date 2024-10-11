package dig;

import dev.langchain4j.model.openai.OpenAiChatModel;

import static dev.langchain4j.model.openai.OpenAiChatModelName.GPT_4_O_MINI;

public class OpenAiService {

    public static String getMachineRecommendations(String projectDescription) {
        // Example prompt for OpenAI
        String prompt = "Based on this project description: " + projectDescription +
                ", suggest the machines required to complete the project.";
        String p = "What is 1 + 1?";
        OpenAiChatModel model = OpenAiChatModel.builder()
                .apiKey("demo")
                .modelName(GPT_4_O_MINI)
                .build();
        String answer = model.generate(p);
        System.out.println(answer);

        return answer;
    }
}

