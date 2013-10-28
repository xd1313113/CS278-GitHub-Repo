package dixiao.client.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import dixiao.client.Agent;
import dixiao.common.Item;
import dixiao.common.Requests;

public class AgentTest {
	Requests req;
	Item item;

	@Before
	public void setUp() throws Exception {
		req = mock(Requests.class);
		item = mock(Item.class);
	}

	@Test
	public void testSumitOrder() {
		Agent agent = new Agent();
		when(req.getItem()).thenReturn(item);
		when(req.getAction()).thenReturn("unittest");
		agent.sumitOrder(req);
		verify(req).getItem();
		verify(req).getAction();
	}

}
