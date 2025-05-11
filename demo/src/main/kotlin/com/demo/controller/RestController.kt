package com.demo.controller
import com.demo.enums.TaxType
import com.demo.model.Returns
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class RestController {
    data class ApiResponse(val message: String, val status: String)


    @GetMapping ("/getReturns")
    fun getReturns(
        @RequestParam traderId: Int,
        @RequestParam playedAmount: Double,
        @RequestParam odd: Double
    ) : List<Returns>
    {
        val taxRate = 0.1
        val taxAmountForGeneralTax = 2.0
        val taxAmountForWinningsTax = 1.0


        val possibleReturnAmountBeforeTax = playedAmount * odd;
        val returnsTaxGeneralRate = Returns(possibleReturnAmountBeforeTax, taxRate, 0.0, TaxType.GENERAL, playedAmount)
        val returnsTaxGeneralAmount = Returns(possibleReturnAmountBeforeTax, 0.0, taxAmountForGeneralTax, TaxType.GENERAL, playedAmount)
        val returnsTaxWinningsRate = Returns(possibleReturnAmountBeforeTax, taxRate, 0.0, TaxType.WINNINGS, playedAmount)
        val returnsTaxWinningsAmount = Returns(possibleReturnAmountBeforeTax, 0.0, taxAmountForWinningsTax, TaxType.WINNINGS, playedAmount)

        val allReturns = listOf<Returns>(returnsTaxGeneralRate, returnsTaxGeneralAmount,returnsTaxWinningsRate, returnsTaxWinningsAmount)

        return allReturns
    }
}