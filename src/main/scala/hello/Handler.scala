package hello

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import org.apache.logging.log4j.{LogManager, Logger}

import scala.collection.JavaConverters

class Handler extends RequestHandler[Request, Response] {

  val logger: Logger = LogManager.getLogger(getClass)

  def handleRequest(input: Request, context: Context): Response = {
    logger.info(s"Received a request: $input")
    Response(s"input = $input", input)
  }
}

class ApiGatewayHandler extends RequestHandler[Request, ApiGatewayResponse] {

  def handleRequest(input: Request, context: Context): ApiGatewayResponse = {
    val headers = Map("x-custom-response-header" -> "my custom response header value")

    ApiGatewayResponse(200, s"This is ApiGatewayResponse, input = $input",
      JavaConverters.mapAsJavaMap[String, Object](headers), true)
  }
}
