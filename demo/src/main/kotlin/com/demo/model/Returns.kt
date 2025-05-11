package com.demo.model

import com.demo.enums.TaxType
import jakarta.persistence.*

@Entity
class Returns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var possibleReturnAmount: Double = 0.0
    var possibleReturnAmountBeforeTax: Double = 0.0
    var possibleReturnAmountAfterTax: Double = 0.0
    var taxRate: Double = 0.0
    var taxAmount: Double = 0.0
    var taxType: TaxType = TaxType.GENERAL
    constructor() {}

    /**
     * Constructs Returns based on input parameters.
     * The possible amount after tax is calculated based on taxRate, taxAmount and taxType
     * If taxRate is not 0, taxAmount must be 0 and vice versa.
     *
     * When taxRate is used, subtract percentage from possible amount.
     * When taxAmount is used, subtract the amount directly.
     * When GENERAL tax type is used, tax the entire amount.
     * When WINNINGS tax type is used, tax the winnings only.
     * */
    constructor(
        possibleReturnAmountBeforeTax: Double,
        taxRate: Double,
        taxAmount: Double,
        taxType: TaxType,
        playedAmount: Double
    ) {
        this.possibleReturnAmountBeforeTax = possibleReturnAmountBeforeTax
        if (taxType == TaxType.GENERAL) {
            if (taxRate > 0.0) {
                this.taxRate = taxRate
                this.possibleReturnAmountAfterTax =
                    possibleReturnAmountBeforeTax - (possibleReturnAmountBeforeTax * taxRate)
            } else if (taxAmount > 0.0) {
                this.taxAmount = taxAmount
                this.possibleReturnAmountAfterTax = possibleReturnAmountBeforeTax - taxAmount
            }
            this.possibleReturnAmount = this.possibleReturnAmountAfterTax
        } else if (taxType == TaxType.WINNINGS) {
            val winnings = possibleReturnAmountBeforeTax - playedAmount
            if (taxRate > 0.0) {
                this.taxRate = taxRate
                this.possibleReturnAmountAfterTax =  possibleReturnAmountBeforeTax - (winnings * taxRate)
            } else if (taxAmount > 0.0) {
                this.taxAmount = taxAmount
                this.possibleReturnAmountAfterTax = winnings - taxAmount
            }
            this.possibleReturnAmount = this.possibleReturnAmountAfterTax
        }
        this.taxType = taxType
    }
}