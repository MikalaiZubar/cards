package com.micro.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
        name = "Card",
        description = "Schema to hold Card data"
)
public class CardDto {

    @NotBlank
    @Pattern(regexp = "[\\d]{12}", message = "mobileNumber should contain 12 digits")
    @Schema(description = "mobile number associated with card", example = "370123456789")
    private String mobileNumber;

    @Pattern(regexp = "^45[\\d]{14}", message = "cardNumber should contain 16 digits and start from 45")
    @Schema(description = "card number", example = "4501000200030004")
    private String cardNumber;

    @Schema(description = "card type", example = "CREDIT CARD")
    private String cardType;

    @Schema(description = "card total credit limit", example = "100000")
    private long totalLimit;

    @Schema(description = "card credit limit used amount", example = "1000")
    private long amountUsed;

    @Schema(description = "card credit limit available amount (totalLimit - amountUsed)", example = "99000")
    private long availableAmount;
}
