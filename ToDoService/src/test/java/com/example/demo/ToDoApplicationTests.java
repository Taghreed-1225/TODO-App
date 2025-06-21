package com.example.demo;

import com.example.demo.controller.TodoController;
import com.example.demo.entity.Items;
import com.example.demo.service.ServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class ToDoApplicationTests {
	@InjectMocks
	private TodoController todoController;

	@Mock
	private ServiceImp serviceImp;

	@Mock
	private BindingResult bindingResult;

	// هنستخدمه بطريقة غير مباشرة فممكن نعمله Spy أو نخليه method overridable

	@Test
	void testAddItem_ValidTokenAndNoErrors_ReturnsCreated() {
		// Arrange
		Items item = new Items();
		String token = "valid-token";

		when(bindingResult.hasErrors()).thenReturn(false);
		when(serviceImp.addItem(item)).thenReturn("Item added successfully");

		// نستخدم Spy علشان نتحكم في isTokenValid()
		TodoController controller = Mockito.spy(todoController);
		doReturn(true).when(controller).isTokenValid(token);

		// Act
		ResponseEntity<String> response = controller.addItem(item, token, bindingResult);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Item added successfully", response.getBody());
	}

	@Test
	void testAddItem_InvalidToken_ReturnsUnauthorized() {
		Items item = new Items();
		String token = "invalid-token";

		when(bindingResult.hasErrors()).thenReturn(false);

		TodoController controller = Mockito.spy(todoController);
		doReturn(false).when(controller).isTokenValid(token);

		ResponseEntity<String> response = controller.addItem(item, token, bindingResult);

		assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
		assertEquals("Invalid token", response.getBody());
	}

	@Test
	void testAddItem_HasValidationErrors_ReturnsBadRequest() {
		Items item = new Items();
		String token = "any-token";

		when(bindingResult.hasErrors()).thenReturn(true);
		ObjectError error = new ObjectError("item", "Name is required");
		when(bindingResult.getAllErrors()).thenReturn(List.of(error));

		ResponseEntity<String> response = todoController.addItem(item, token, bindingResult);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(response.getBody().contains("Validation failed"));
	}


}
