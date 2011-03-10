package org.elasticsearch.orm.hibernate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.elasticsearch.orm.hibernate.testcase1.EntityMaker;
import org.elasticsearch.orm.hibernate.testcase1.SimpleEntity;
import org.junit.Test;

/**
 * @author PILATO
 *
 */
public class JSonBuilderTest {

	private String generateJsonFromEntity(Object entity, String expected) {
		ObjectMapper mapper = new ObjectMapper();

//		mapper.getSerializationConfig().setAnnotationIntrospector(new ElasticSearchHibernateAnnotationIntrospector());
		mapper.registerModule(new ElasticSearchJacksonHibernateModule());
		
		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.configure(SerializationConfig.Feature.AUTO_DETECT_FIELDS, true);
		mapper.configure(SerializationConfig.Feature.AUTO_DETECT_GETTERS, false);
		mapper.configure(SerializationConfig.Feature.AUTO_DETECT_IS_GETTERS, false);
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

		String s = null;
		try {
			s = mapper.writeValueAsString(entity);
		} catch (JsonGenerationException e) {
			fail("JsonGenerationException : " + e.getMessage());
		} catch (JsonMappingException e) {
			fail("JsonMappingException : " + e.getMessage());
		} catch (IOException e) {
			fail("IOException : " + e.getMessage());
		}
		
		assertNotNull(s);
		
		if (expected != null) assertEquals(expected, s);

		System.out.println(s);
		return s;
	}
	
	@Test
	public void testModelEntity1() throws IOException {
		generateJsonFromEntity(EntityMaker.getEntity1(), null);
	}
	
	@Test
	public void testModelEntity2() throws IOException {
		generateJsonFromEntity(EntityMaker.getEntity2(), null);
	}
	
	@Test
	public void testModelEntity3_1() throws IOException {
		generateJsonFromEntity(EntityMaker.getEntity3_1(), null);
	}
	
	@Test
	public void testModelEntity3_2() throws IOException {
		generateJsonFromEntity(EntityMaker.getEntity3_2(), null);
	}
	
	@Test
	public void testModelEntity4_1() throws IOException {
		generateJsonFromEntity(EntityMaker.getEntity4_1(), null);
	}
	
	@Test
	public void testModelEntity4_2() throws IOException {
		generateJsonFromEntity(EntityMaker.getEntity4_2(), null);
	}
	
}
