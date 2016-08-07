package demo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import demo.dao.AdjectiveClient;
import demo.dao.ArticleClient;
import demo.dao.NounDaoImpl;
import demo.dao.SubjectClient;
import demo.dao.VerbClient;
import demo.dao.WordDao;
import demo.domain.Word;

public class SentenceServiceImplTest {

	//	Class under test:
	SentenceServiceImpl service;
	
	@Before
	public void setup() {

		service = new SentenceServiceImpl();
		
		//	Establish Mock Dependencies:
		WordDao subject = Mockito.mock(SubjectClient.class);
		WordDao verb = Mockito.mock(VerbClient.class);
		WordDao article = Mockito.mock(ArticleClient.class);
		WordDao adjective = Mockito.mock(AdjectiveClient.class);
		WordDao noun = Mockito.mock(NounDaoImpl.class);

		service.setSubjectService(subject);
		service.setVerbService(verb);
		service.setArticleService(article);
		service.setAdjectiveService(adjective);
		service.setNounService(noun);
		
		//	Describe Mock Behaviors:
		Mockito.when(subject.getWord()).thenReturn(new Word("1"));
		Mockito.when(verb.getWord())	.thenReturn(new Word("2"));
		Mockito.when(article.getWord())	.thenReturn(new Word("3"));
		Mockito.when(adjective.getWord()).thenReturn(new Word("4"));
		Mockito.when(noun.getWord())	.thenReturn(new Word("5"));
	}
	
	@Test
	public void test() {
		//	We should get the sentence built in the correct order:
		Assert.assertEquals("1 2 3 4 5.", service.buildSentence());
	}

}
