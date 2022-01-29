package myapp.models;



import javax.persistence.*;

@Entity @Table(name="users")
public class user{

	



	@Override
	public String toString() {
		return "user [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", type_user=" + type_user + ", email="
				+ email + ", password=" + password + "]";
	}

	public user() {
		super();
	}
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String prenom;
	private int type_user;
	private String email;
	private String password;
	


	public user(int id, String nom, String prenom, int type_user, String email,String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.type_user = type_user;
		this.email = email;
		this.password=password;
	}

	public user(String nom, String prenom, int type_user, String email,String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.type_user = type_user;
		this.email = email;
		this.password=password;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getType_user() {
		return type_user;
	}

	public void setType_user(int type_user) {
		this.type_user = type_user;
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
