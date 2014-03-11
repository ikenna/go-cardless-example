import com.typesafe.config.ConfigFactory
import gocardless.connect.{Connect, Subscription}
import gocardless.{GoCardless, AccountDetails}
import java.io.File

// https://dashboard-sandbox.gocardless.com/customers
// https://gocardless.com/docs
object GoCardlessEvaluation extends App {

  GoCardless.environment = GoCardless.Environment.SANDBOX
  
  val accountDetails = AccountDetailsBuilder()

  val connect = new Connect(accountDetails)

  val subscription = new Subscription(AccountDetailsBuilder.merchantId, new java.math.BigDecimal(15.0), 1, "month")
  val authorisationUrl = connect.newSubscriptionUrl(subscription, "https://somedomain.theguardian.com", "https://somedomain.theguardian.com", null)
  
  println(authorisationUrl)
}

object AccountDetailsBuilder {

  private lazy val conf = ConfigFactory.parseFile(new File(
    System.getProperty("user.home") + "/.config/goCardlessSandbox.json")
  )

  lazy val merchantId = conf.getString("merchantId")
  
  def apply(): AccountDetails = {
    val accountDetails = new AccountDetails()
    accountDetails.setAppId(conf.getString("appIdentifier"))
    accountDetails.setAppSecret(conf.getString("appSecret"))
    accountDetails.setAccessToken(conf.getString("merchantAccessToken"))
    accountDetails
  }
}
