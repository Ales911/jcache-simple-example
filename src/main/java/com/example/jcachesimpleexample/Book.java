package com.example.jcachesimpleexample;

import java.io.Serializable;

public record Book(String isbn, String author, String title) implements Serializable {}