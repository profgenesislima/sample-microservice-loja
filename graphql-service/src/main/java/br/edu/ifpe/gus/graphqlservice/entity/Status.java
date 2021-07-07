package br.edu.ifpe.gus.graphqlservice.entity;

public enum Status {

	ACTIVE,NON_ACTIVE;
	
	public Status fromValue(String value) {
        return valueOf(value.toUpperCase());
    }
    public String toValue(Status status) {
        return status.name().toLowerCase();
    }
    
}
