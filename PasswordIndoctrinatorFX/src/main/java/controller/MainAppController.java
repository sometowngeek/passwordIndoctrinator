package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

public class MainAppController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblEnterPasswordToMemorize;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblRetypeThePassword;

    @FXML
    private PasswordField pwfPasswordToMemorize;

    @FXML
    private PasswordField pwfPasswordAttempt;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnCheck;
    
    @FXML
    private VBox vboxRoot;
    
    private boolean passwordSet;
    
    private PasswordController pwc;
    
    private int count;
    
    private final int MAX_TRIES = 10;

    @FXML
    void handleCheckButtonAction(ActionEvent event) {
    	if (!passwordSet) {
    		setPasswordToMemorize();
    	} else {
    		checkMatchingPassword();
    	}
    }

	private void checkMatchingPassword() {
		String attempt = this.pwfPasswordAttempt.getText();
		
		if(attempt != null && !attempt.trim().isEmpty()) {
			boolean result = this.pwc.checkPasswordMatch(attempt);
			if(result) {
				this.setRootBorder("success");
				this.pwfPasswordAttempt.setText("");
				this.count++;
				
				if(count== MAX_TRIES) {
					this.lblStatus.setText("You've matched it 10 times in a row!\nI think you got it memorized.");
					this.pwfPasswordAttempt.setDisable(true);
				} else {
					this.lblStatus.setText(String.format("Matches! Do it again!    Matched %1$d/10", this.count));
				}
			} else {
				this.setRootBorder("error");
				this.lblStatus.setText("Whoops... try again!");
				this.pwfPasswordAttempt.setText("");
				this.count = 0;
			}
			
			this.btnCheck.setDisable(true);
		}
	}

	private void setPasswordToMemorize() {
		String toMemorize = this.pwfPasswordToMemorize.getText();
		
		if (toMemorize != null && !toMemorize.trim().isEmpty()) {
			this.pwc = PasswordController.setPassword(this.pwfPasswordToMemorize.getText());
			this.passwordSet = true;
			this.pwfPasswordToMemorize.setDisable(true);
			this.pwfPasswordAttempt.setDisable(false);
			this.lblStatus.setText("Start!");
			this.count = 0;
			this.btnCheck.setDisable(true);
		}
	}

    @FXML
    void handleResetButtonAction(ActionEvent event) {
    	this.prepareStartup();
    }
    
    @FXML
    void handlePasswordToMemorizeKeyTypedAction(KeyEvent event) {
    	String toMemorize = this.pwfPasswordToMemorize.getText();
    	String key = event.getCharacter();
    	
    	if(toMemorize != null && !toMemorize.trim().isEmpty()) {
    		this.btnCheck.setDisable(false);
    	} else if(key != null && !key.trim().isEmpty()) {
    		this.btnCheck.setDisable(false);
    	} else {
    		this.btnCheck.setDisable(true);
    	}
    }
    
    @FXML
    void handlePasswordAttemptKeyTypedAction(KeyEvent event){
    	String pwAttempt = this.pwfPasswordAttempt.getText();
    	String key = event.getCharacter();
    	
    	if(pwAttempt != null && !pwAttempt.trim().isEmpty()) {
    		this.btnCheck.setDisable(false);
    	} else if(key != null && !key.trim().isEmpty()) {
    		this.btnCheck.setDisable(false);
    	} else {
    		this.btnCheck.setDisable(true);
    	}
    }

    @FXML
    void initialize() {
        assert lblEnterPasswordToMemorize != null : "fx:id=\"lblEnterPasswordToMemorize\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        assert lblStatus != null : "fx:id=\"lblStatus\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        assert lblRetypeThePassword != null : "fx:id=\"lblRetypeThePassword\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        assert pwfPasswordToMemorize != null : "fx:id=\"pwfPasswordToMemorize\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        assert pwfPasswordAttempt != null : "fx:id=\"pwfPasswordAttempt\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        assert vboxRoot != null : "fx:id=\"vboxRoot\" was not injected: check your FXML file 'PasswordIndoctrinator.fxml'.";
        this.prepareStartup();
    }
    
    private void prepareStartup() {
    	this.pwfPasswordToMemorize.setText("");
    	this.pwfPasswordToMemorize.setDisable(false);
    	this.pwfPasswordAttempt.setText("");
    	this.pwfPasswordAttempt.setDisable(true);
    	this.lblStatus.setText("");
    	this.passwordSet = false;
    	this.pwc = null;
    	this.btnCheck.setDisable(true);
    	this.count = 0;
    	this.setRootBorder("");
    }

	private void setRootBorder(String style) {
		ObservableList<String> styles = this.vboxRoot.getStyleClass();
		boolean containsError = styles.contains("error");
		boolean containsSuccess = styles.contains("success");
		
		switch (style) {
		case "error":
			if(!containsError) { styles.add("error"); }
			if(containsSuccess) { styles.remove("success"); }
			break;
		case "success":
			if(containsError) { styles.remove("error"); }
			if(!containsSuccess) { styles.add("success"); }
			break;
		default:
			if(containsError) { styles.remove("error"); }
			if(containsSuccess) { styles.remove("success"); }
			break;
		}
	}
}