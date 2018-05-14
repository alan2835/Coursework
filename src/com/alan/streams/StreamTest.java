package com.alan.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Movie {
	private String name;
	private String title;
	private int rank;
	
	public Movie(String name, String title, int rank) {
		this.name = name;
		this.title = title;
		this.rank = rank;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public boolean isClassic() {
		return rank == 1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Movie [name=" + name + ", title=" + title + ", rank=" + rank + "]";
	}
	
}

class MovieUtil {
	public static List<Movie> getMovies(){
		List<Movie> movies = new ArrayList<>();
		movies.add(new Movie("Test", "Tile of Test", 1));
		movies.add(new Movie("Really", "Backstreet", 2));
		movies.add(new Movie("Lego", "Ninjago", 2));
		movies.add(new Movie("ET", "ExtraTerrestrial", 1));
		return movies;
	}
}

public class StreamTest {
	
	private static void testEmpltyStream() {
		Stream<Integer> movies = Stream.empty();
		
		System.out.println("Empty Stream: " + movies.count());
	}
	
	private static void testStringStream() {
		Stream<String> movies = Stream.of("ET","Ninjagos");
		System.out.println("Empty Stream: " + movies.count());
		
		String[] movieNames = {"ET","Ninjago"};
		Stream<String> movies2 = Stream.of(movieNames);
		System.out.println("Empty Stream: " + movies2.count());
	}
	
	private static void testCollection() {
		List<Movie> movies = MovieUtil.getMovies();
		
		movies.stream().forEach(System.out::println);
		
		movies.parallelStream()
			.filter(m -> m.isClassic())
			//.filter(Movie::isClassic)
			.map(Movie::getName)
			.limit(2)
			.forEach(System.out::println);
	}
	
	private static void infinateStream() {
		Stream<Double> random = Stream.generate(Math::random);
		//random
			//.map(d -> Math.round(d.doubleValue()))
			//.forEach(System.out::println);
		
		Stream<Integer> numbers = Stream.iterate(1, i -> i+1);
		numbers.forEach(System.out::println);
	}
	
	private static void testFiles() {
		try (Stream<String> fileLines = Files.lines(Paths.get("C:\\bah\\awsNotes.txt"))) {
			fileLines.forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());		
		testStringStream();
		testCollection();
		//testFiles();

	}

}
