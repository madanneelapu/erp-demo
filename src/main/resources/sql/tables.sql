CREATE TABLE `madan_om_dochead` (
  `SerialNo` int unsigned NOT NULL AUTO_INCREMENT,
  `DocOrderNo` varchar(55) NOT NULL DEFAULT ' ',
  `DocOrderType` varchar(45) NOT NULL DEFAULT ' ',
  `ReceivedDate` timestamp NULL DEFAULT NULL,
  `ReqBy` varchar(45) DEFAULT ' ',
  `TotalAmount` decimal(15,2) DEFAULT NULL,
  `Status` int unsigned NOT NULL DEFAULT 2,
  PRIMARY KEY (`SerialNo`, `DocOrderNo`, `DocOrderType`)
);

CREATE TABLE `madan_om_docorderitems` (
  `SerialNo` int unsigned NOT NULL AUTO_INCREMENT,
  `DocOrderNo` varchar(55) NOT NULL DEFAULT ' ',
  `DocOrderType` varchar(45) NOT NULL DEFAULT ' ',
  `ReceivedDate` timestamp NULL DEFAULT NULL,  -- stores in UTC
  `ItemNo` int unsigned NOT NULL DEFAULT 0,
  `ItemCode` varchar(45) DEFAULT ' ',
  `Price` decimal(15,4) DEFAULT NULL,
  `Quantity` decimal(15,4) DEFAULT NULL,
  `TotAmount` decimal(18,4) DEFAULT NULL,
  `Priority` varchar(45) DEFAULT ' ',
  `Status` int unsigned NOT NULL DEFAULT 2,
  PRIMARY KEY (`SerialNo`, `DocOrderNo`, `DocOrderType`, `ItemNo`)
);


CREATE TABLE `madan_om_docorditemstatus` (
  `SerialNo` int unsigned NOT NULL AUTO_INCREMENT,
  `DocOrderNo` varchar(55) NOT NULL,
  `DocOrderType` varchar(45) NOT NULL,
  `ItemNo` int unsigned NOT NULL,
  `Status` int unsigned NOT NULL DEFAULT 2,
  `StatusDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Remarks` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`SerialNo`),
  INDEX `idx_docitem_status` (`DocOrderNo`, `DocOrderType`, `ItemNo`, `Status`)
);
