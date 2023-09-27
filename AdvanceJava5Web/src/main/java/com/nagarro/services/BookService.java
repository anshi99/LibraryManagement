package com.nagarro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.nagarro.constants.Constants;
import com.nagarro.models.Author;
import com.nagarro.models.Book;

import interfaces.BookServiceInterface;

@Component
public class BookService implements BookServiceInterface{
	@Autowired
	WebClientService webClient;

	@Override
	public Book[] fetchAllBooks() {
		Book[] books = null;
		try {
			books = webClient.getWebClient().get().uri(Constants.pathMaping.BOOKS).retrieve().bodyToMono(Book[].class).block();
		} catch (WebClientResponseException wcre) {
			System.out.println(Constants.Exceptions.RETRIEVING_RESPONSE_ERROR);
		} catch (WebClientException wce) {
			System.out.println(Constants.Exceptions.RESPONSE_BODY_PARSING_ERROR);
		} catch (Exception e) {
			System.out.println(Constants.Exceptions.UNKNOWN_ERROR);
		}
		return books;
	}

	@Override
	public Book fetchBookByID(int bid) {
		Book book = null;
		try {
			book = webClient.getWebClient().get().uri("/books?bid=" + bid).retrieve().bodyToMono(Book.class).block();
		} catch (WebClientResponseException wcre) {
			System.out.println(Constants.Exceptions.RETRIEVING_RESPONSE_ERROR);
		} catch (WebClientException wce) {
			System.out.println(Constants.Exceptions.RESPONSE_BODY_PARSING_ERROR);
		} catch (Exception e) {
			System.out.println(Constants.Exceptions.UNKNOWN_ERROR);
		}
		System.out.println(book);

		return book;
	}


	@Override
	public Author[] fetchAllAuthors() {
		Author[] authors = null;

		try {
			authors = webClient.getWebClient().get().uri(Constants.pathMaping.BOOKSAUTHOR).retrieve().bodyToMono(Author[].class).block();
		} catch (WebClientResponseException wcre) {
			System.out.println(Constants.Exceptions.RETRIEVING_RESPONSE_ERROR);
		} catch (WebClientException wce) {
			System.out.println(Constants.Exceptions.RESPONSE_BODY_PARSING_ERROR);
		} catch (Exception e) {
			System.out.println(Constants.Exceptions.UNKNOWN_ERROR);
		}

		return authors;
	}

	@Override
	public String addBook(Object library) {
		System.out.println(library);
		String response = webClient.getWebClient().post().uri(Constants.pathMaping.BOOKSADD).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(library)).retrieve()
				.bodyToMono(String.class).block();
		System.out.println(response);
		return response;
	}

	@Override
	public String updateBook(int bid, Object library) {
		String response = webClient.getWebClient().post().uri("/books/update?bid=" + bid).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(library)).retrieve()
				.bodyToMono(String.class).block();
		System.out.println(response);

		return response;
	}

	@Override
	public String deleteBook(int bid) {
		String response = webClient.getWebClient().delete().uri("/books?bid=" + bid).accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(String.class).block();

		System.out.println(response);

		return response;
	}
}
