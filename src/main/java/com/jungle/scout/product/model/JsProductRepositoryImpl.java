package com.jungle.scout.product.model;

import com.jungle.scout.product.model.JsProduct;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JsProductRepositoryImpl implements JsProductCustom {
	
	@PersistenceContext
	EntityManager entityManager;
	


	@Override
	@Transactional
	public List<JsProduct> findAndInsertIfNotThere(String id) {
		String hql = "select js from JsProduct js where js.ASIN = :id";
		TypedQuery<JsProduct> jsQuery = entityManager.createQuery(hql, JsProduct.class);
		jsQuery.setParameter("id", id);
		
		List<JsProduct> result = jsQuery.getResultList();
		
		if(result.isEmpty()) {
			
			hql = "select amz from AmzProduct amz where amz.ASIN = :id";
			TypedQuery<AmzProduct> amzQuery = entityManager.createQuery(hql, AmzProduct.class);
			amzQuery.setParameter("id", id);
			List<AmzProduct> amzResult = amzQuery.getResultList();
			
			if(!amzResult.isEmpty()) {
				hql = "insert into JsProduct(ASIN, category, rank, dimensions)"
						+ " select amz.ASIN, amz.category, amz.rank, amz.dimensions from AmzProduct amz"
						+ " where amz.ASIN = :id";
				Query tempQuery = entityManager.createQuery(hql);//, JsProduct.class
				tempQuery.setParameter("id", amzResult.get(0).getASIN()).executeUpdate();
				
				hql = "select js from JsProduct js where js.ASIN = :id";
				jsQuery = entityManager.createQuery(hql, JsProduct.class);
				jsQuery.setParameter("id", id);
				
				result = jsQuery.getResultList();

			}
		}
		
		return result;
	}


}
