package dixiao.common.test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import dixiao.common.BuyItemOrder;
import dixiao.common.ItemActions;

public class BuyItemOrderTest {
	ItemActions actions;
	@Before
	public void setUp() throws Exception {
		actions = mock(ItemActions.class);
	}

	@Test
	public void testExecute() {
		BuyItemOrder buyOrder = new  BuyItemOrder(actions);
		buyOrder.execute();
		verify(actions).buy();
	}
}
