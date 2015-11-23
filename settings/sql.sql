--
-- Database: `smartBanking`
--

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_bin NOT NULL,
  `password` varchar(20) COLLATE utf8_bin NOT NULL,
  `client_id` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `client_secret` varchar(6) COLLATE utf8_bin DEFAULT NULL,
  `activation_code` varchar(6) COLLATE utf8_bin NOT NULL,
  `access_token` varchar(36) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`id`, `username`, `password`, `client_id`, `client_secret`, `activation_code`, `access_token`) VALUES
(1, 'Elnaz', 'eli', '1d9dcd9h14h', 'nb314t', 'xr91Ji', '5e4ac3b8-c55c-4315-987c-f8e0a7d66d91');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `login`
--
ALTER TABLE `login`
  ADD PRIMARY KEY (`id`);