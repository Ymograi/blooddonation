# Include the Autoloader (see "Libraries" for install instructions)
require 'vendor/autoload.php';
use Mailgun\Mailgun;

# Instantiate the client.
$mgClient = new Mailgun('key-b4bf8902ea590166bd68e8e071df0d69');
$domain = "sandboxbe6d90b1acb44ecfb7ac09375df26801.mailgun.org";

# Make the call to the client.
$result = $mgClient->sendMessage("$domain",
                  array('from'    => 'Mailgun Sandbox <postmaster@sandboxbe6d90b1acb44ecfb7ac09375df26801.mailgun.org>',
                        'to'      => 'Abin Bhattacharya <abinbhattacharya@gmail.com>',
                        'subject' => 'Hello Abin Bhattacharya',
                        'text'    => 'Congratulations Abin Bhattacharya, you just sent an email with Mailgun!  You are truly awesome!  You can see a record of this email in your logs: https://mailgun.com/cp/log .  You can send up to 300 emails/day from this sandbox server.  Next, you should add your own domain so you can send 10,000 emails/month for free.'));
