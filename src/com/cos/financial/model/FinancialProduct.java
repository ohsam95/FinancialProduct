package com.cos.financial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FinancialProduct {
	
	private int id;
	private String name;
	private String bankname;
	private String productType;
}
