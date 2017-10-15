package dto;

public class UserDTO {
	private Long id;
	private String email;
	private String password;
	
	public UserDTO() {}
	
	public UserDTO(String _email, String _password) {
		this.email = _email;
		this.password = _password;
		this.id = -1L;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
