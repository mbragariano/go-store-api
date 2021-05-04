package br.com.mbragariano.gostoreapi.contexts.product.presentation.controllers.cases;

import br.com.mbragariano.gostoreapi.contexts.product.persistence.documents.ProductDocument;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.controllers.stubs.RegisterProductControllerStub;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures.DuplicatedProductNameResponse;
import br.com.mbragariano.gostoreapi.contexts.product.presentation.models.responses.failures.InvalidProductDataResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private MongoTemplate mongoTemplate;

  @BeforeEach
  public void before() {
    this.mongoTemplate.remove(ProductDocument.class).all();
  }

  @ValueSource(booleans = {true, false})
  @ParameterizedTest(name = "handle_whenProvideRequest_withInvalidData_shouldReturnBadRequest: nullable = {0}")
  public void handle_whenSendRequest_withInvalidData_shouldReturnBadRequest_withInvalidProductDataResponse(boolean nullable) throws Exception {
    final var response = RegisterProductControllerStub.getInvalidProductDataResponse();
    final var request = RegisterProductControllerStub.getInvalidRegisterProductRequest(nullable);

    final var content = this.objectMapper.writeValueAsString(request);
    final var requestBuilder = this.getRequestBuilder(content);

    this.mockMvc.perform(requestBuilder)
      .andExpect(status().isBadRequest())
      .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
      .andExpect(jsonPath("$.message").value(response.message))
      .andExpect(jsonPath("$.description").value(response.description))
      .andExpect(jsonPath("$.developer_message").doesNotHaveJsonPath())
      .andExpect(jsonPath("$.validations").isArray())
      .andExpect(jsonPath("$.validations", hasSize(3)))
      .andExpect(jsonPath("$.response_type").value(InvalidProductDataResponse.class.getSimpleName()))
      .andDo(print());
  }

  @Test
  public void handle_whenSendRequest_withExistentProductName_shouldReturnConflict_withDuplicatedProductNameResponse() throws Exception {
    final var request = RegisterProductControllerStub.getDuplicatedProductRequest();
    final var response = RegisterProductControllerStub.getDuplicatedProductNameResponse();

    final var content = this.objectMapper.writeValueAsString(request);
    final var requestBuilder = this.getRequestBuilder(content);

    this.mockMvc.perform(requestBuilder)
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andDo(print());

    this.mockMvc.perform(requestBuilder)
      .andExpect(status().isConflict())
      .andExpect(content().contentType(MediaType.APPLICATION_PROBLEM_JSON_VALUE))
      .andExpect(jsonPath("$.message").value(response.message))
      .andExpect(jsonPath("$.description").value(response.description))
      .andExpect(jsonPath("$.developer_message").doesNotHaveJsonPath())
      .andExpect(jsonPath("$.response_type").value(DuplicatedProductNameResponse.class.getSimpleName()))
      .andDo(print());
  }

  @Test
  public void handle_whenSendRequest_withValidData_and_withNonexistentProductName_shouldReturnCreated_withRegisterProductResponse() throws Exception {
    final var request = RegisterProductControllerStub.getRegisterProductRequest();

    final var content = this.objectMapper.writeValueAsString(request);
    final var requestBuilder = this.getRequestBuilder(content);

    this.mockMvc.perform(requestBuilder)
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").isString())
      .andExpect(jsonPath("$.id").isNotEmpty())
      .andExpect(jsonPath("$.name").value(request.name))
      .andExpect(jsonPath("$.price").value(request.price))
      .andExpect(jsonPath("$.description").value(request.description))
      .andDo(print());
  }

  private MockHttpServletRequestBuilder getRequestBuilder(String content) {
    return post("/products").contentType(MediaType.APPLICATION_JSON_VALUE).content(content);
  }

}
