public class FlashCard {
	private String front; //the front data of the flash card; 'question data'
	private String back; //the back data of the flashcard; 'answer data'
	private String type; //identify the type of flash if the user wants to group them somehow? may implement more identifiers in
	//future versions
	
	Boolean answered= false; //marks if the flash card has been answered correctly
	
	public FlashCard(String front, String back, String type){
		this.front= front;
		this.back= back;
		this.type= type;
	}
	
	public FlashCard(){
		this("","","");
	}
	
	public FlashCard(String front, String back){
		this(front, back, "");
	}
	
	public String getFront() {
		return front;
	}
	
	public String getBack() {
		return back;
	}
	
	public Boolean getBoolean() {
		return answered;
	}
	
	public void setFront(String front) {
		this.front= front;	
	}
	
	public void setBack(String back) {
		this.back= back;
	}
	
	public void setAnswered(Boolean answered) {
		this.answered= answered;
	} 
	
	public Boolean getAnswered() {
		return answered;
	}
	
	public String toString() {
		String s= getFront() + "," + getBack();
		return s;
	}
	
	
}