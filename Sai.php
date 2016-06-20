 <html>
<head>
<title>Toll Data </title>
</head>
<body>
<?php
    $xml_data = '<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <GetTollPlazaInfo xmlns="http://tempuri.org/" />
  </soap:Body>
</soap:Envelope>';

$URL = "http://nhtis.org/TollPlazaService.asmx";

$ch = curl_init($URL);
curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: text/xml'));
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, "$xml_data");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch);
curl_close($ch);

$myfile = fopen("Toll_Road.xml", "w") or die("Unable to open file!");
fwrite($myfile, $output);
fclose($myfile);


print_r($output.count);
?>
</body>
</html>
