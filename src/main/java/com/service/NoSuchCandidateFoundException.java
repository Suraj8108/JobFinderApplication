package com.service;

public class NoSuchCandidateFoundException extends Exception {

    public NoSuchCandidateFoundException(int id) {
        
        super(id+""+" not found");
        // TODO Auto-generated constructor stub
    }

}
