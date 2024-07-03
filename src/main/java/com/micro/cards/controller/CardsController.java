package com.micro.cards.controller;

import com.micro.cards.dto.CardDto;
import com.micro.cards.dto.ErrorResponseDto;
import com.micro.cards.dto.ResponseDto;
import com.micro.cards.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.micro.cards.constants.CardsConstants.*;

@Tag(
        name = "CRUD REST API for Cards in Bank services.",
        description = "APIs to CREATE, UPDATE, GET and DELETE operations."
)
@RestController
@RequestMapping(path = "/api/v1/cards", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CardsController {

    private final CardsService cardsService;

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create a new Card inside Bank application"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP status CREATED"
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                      @Pattern(regexp = "[\\d]{12}", message = "mobile number should be 12 digits") String mobileNumber) {
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));

    }

    @Operation(
            summary = "GET Card REST API",
            description = "REST API to fetch a Card details for Bank application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP status NOT_FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<CardDto> getCard(@Valid @RequestParam
                                               @Pattern(regexp = "[\\d]{12}", message = "mobile number should be 12 digits") String mobileNumber) {
        CardDto cardDto = cardsService.getCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardDto);
    }

    @Operation(
            summary = "UPDATE Card REST API",
            description = "REST API to update Card inside Bank application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "HTTP status FAILED",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping
    public ResponseEntity<ResponseDto> updateCard(@RequestBody @Valid CardDto cardDto) {
        boolean isUpdated = cardsService.updateCard(cardDto);
        HttpStatus status = isUpdated ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED;
        String message = isUpdated ? MESSAGE_200 : MESSAGE_417_UPDATE;
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(status.toString(), message));
    }

    @Operation(
            summary = "DELETE Card REST API",
            description = "REST API to delete Card inside Bank application"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP status OK"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "HTTP status NOT_FOUND",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteCard(@RequestBody @Valid CardDto cardDto) {
        cardsService.deleteCard(cardDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), MESSAGE_200));
    }
}
