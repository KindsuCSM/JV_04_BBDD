package model;

import java.awt.Image;
import java.util.Date;
import java.util.GregorianCalendar;

public class Alumno {
	private Integer alumn_id;
	private String user;
	private String password;
	private GregorianCalendar birthday_date;
	private Double average_score;
	private String photo;

	public Alumno() {

	}

	public Alumno(Integer alumn_id, String user, String password, Date birthday_date, Double average_score,
			String photo) {
		setUser(user);
		setPassword(password);
		setBirthday_date(birthday_date);
		setAverage_score(average_score);
		setPhoto(photo);
	}



	public Integer getAlumn_id() {
		return alumn_id;
	}
	public void setAlumn_id(Integer alumn_id) {
		this.alumn_id = alumn_id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public GregorianCalendar getBirthday_date() {
		return birthday_date;
	}
	public void setBirthday_date(Date birthday_date) {
		if(birthday_date == null) {
			this.birthday_date = new GregorianCalendar();
		}else{
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(birthday_date);
			this.birthday_date = calendar;
		}

	}
	public Double getAverage_score() {
		return average_score;
	}
	public void setAverage_score(Double average_score) {
		this.average_score = average_score;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}



}