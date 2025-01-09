package model;

public class Asignatura {
	private String code_subject;
	private String name;
	private Double score;
	private Integer alumn_id;

	public Asignatura() {}

	public Asignatura(String code_subject, String name, Double score, Integer alumn_id) {
		this.code_subject = code_subject;
		this.name = name;
		this.score = score;
		this.alumn_id = alumn_id;
	}
	public String getCode_subject() {
		return code_subject;
	}
	public void setCode_subject(String code_subject) {
		this.code_subject = code_subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Integer getAlumn_id() {
		return alumn_id;
	}
	public void setAlumn_id(Integer alumn_id) {
		this.alumn_id = alumn_id;
	}


}
