package sensetile.eia.camel.prototyp1.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.CamelTestSupport;

public class SinkTest extends CamelTestSupport {

	@Produce(uri = "direct:start")
    protected ProducerTemplate template;

	@EndpointInject(uri = "mock:result")
    protected MockEndpoint resultEndpoint;

	
	public void testSendMatchingMessage() throws Exception {
        String expectedBody = "<fdgdgfdgfdgfdgdfgdfgd/>";

        resultEndpoint.expectedBodiesReceived(expectedBody);

        template.sendBodyAndHeader(expectedBody, "foo", "bar");

        resultEndpoint.assertIsSatisfied();
    }

	
	@Override
    protected RouteBuilder createRouteBuilder() {
        return new RouteBuilder() {
            public void configure() {
                from("direct:start").filter(header("foo").isEqualTo("bar")).to("mock:result");
            }
        };
    }
}
