package model;

public class User {

    private String username;

    private String password;

    private String field1;
    
    private String field2;
    
    private String accounttype;

    public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getUsername() {

        return username;

    }

    public void setUsername(String username) {

        this.username = username;

    }

    public String getPassword() {

        return password;

    }

    public void setPassword(String password) {

        this.password = password;

    }

	public String getfield1() {
		return field1;
	}

	public void setfield1(String field1) {
		this.field1 = field1;
	}

	public String getfield2() {
		return field2;
	}

	public void setfield2(String field2) {
		this.field2 = field2;
	}



}