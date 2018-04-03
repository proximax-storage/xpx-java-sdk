<?php

///echo "---";
require_once('coingate/init.php');

//echo "init success";


use CoinGate\CoinGate;

//\CoinGate\CoinGate::config(array(
//  'environment' => 'live', // sandbox OR live
//  'app_id'      => '7640', 
//  'api_key'     => 'Fewh3cugkdRQiZaUt9E8pm', 
//  'api_secret'  => 'fGoKM0ceA42r6dSjwu5Vxi9RO7Zak8CH'
//));


\CoinGate\CoinGate::config(array(
  'environment' => 'sandbox', // sandbox OR live
  'app_id'      => '1081', 
  'api_key'     => 'Ai0lxyCWOnD4IcKYL5v9uU', 
  'api_secret'  => '2aSBMZIW34Jht9yKROYFonrbAmVwUqkp'
));

$jsonInput = file_get_contents('php://input');
$obj = json_decode($jsonInput);
$orderId = $obj->{'orderId'};
$price = $obj->{'price'};
$currency =  $obj->{'currency'};
$receiveCurrency = $obj->{'receiveCurrency'};
$token = $obj->{'token'};
$title = $obj->{'title'};
$description = $obj->{'description'};

$post_params = array(
                   'order_id'          => $orderId,
                   'price'             => $price,
                   'currency'          => $currency,
                   'receive_currency'  => $receiveCurrency,
                   'callback_url'      => 'https://xarcadetools.spfs.io/xargate/callback/status-change?token='.$token,
                   'cancel_url'        => 'https://xarcadetools.spfs.io/xargate/callback/cancel?order='.$token,
                   'success_url'       => 'https://xarcadetools.spfs.io/xargate/callback/cancel?order='.$token,
                   'title'             => $title,
                   'description'       => $description
               );

$order = \CoinGate\Merchant\Order::create($post_params);

if ($order) {
	$responseArr = array('payment_url'=>$order->payment_url,'bitcoin_uri'=>$order->bitcoin_uri,'btc_amount'=>$order->btc_amount,'bitcoin_address'=>$order->bitcoin_address,'expire_at'=>$order->expire_at);
	http_response_code(200);
	header('Content-Type: application/json');
    echo json_encode($responseArr);
} else {
    # Order Is Not Valid
    http_response_code(500);
    echo "error1";
}

?>





