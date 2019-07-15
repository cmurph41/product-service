package com.jungle.scout.product.model;

import java.util.List;


public interface JsProductCustom  {

	public List<JsProduct> findAndInsertIfNotThere(String id);
}
