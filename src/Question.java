
/**
 * @author zberkowitz
 *
 */
public class Question {

	private String prompt;
	private boolean answer;
	public Question(String prompt, boolean answer) {
		this.prompt = prompt;
		this.answer = answer;
	}
	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public boolean getAnswer() {
		return answer;
	}
	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

}
