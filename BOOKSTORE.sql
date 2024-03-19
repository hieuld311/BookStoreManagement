USE [master]
GO
/****** Object:  Database [BOOKSTORE]    Script Date: 12/12/2022 7:07:46 PM ******/
CREATE DATABASE [BOOKSTORE]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BOOKSTORE', FILENAME = N'D:\BOOKSTORE.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'BOOKSTORE_log', FILENAME = N'D:\BOOKSTORE_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [BOOKSTORE] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BOOKSTORE].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON

USE [BOOKSTORE]
GO

/****** Object:  Table [dbo].[OrderDetails]    Script Date: 12/12/2022 7:07:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrderDetails](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[sku] [varchar](10) NOT NULL,
	[order_id] [int] NOT NULL,
	[price] [money] NOT NULL,
	[quantity] [int] NOT NULL,
	[total] [money] NOT NULL,

 CONSTRAINT [PK_OrderDetails] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

SET IDENTITY_INSERT [dbo].[OrderDetails] ON 
GO
INSERT [dbo].[OrderDetails] ([id], [sku], [order_id], [price], [quantity], [total]) VALUES (1, N'BU7832', 114, 19.9900, 1, 19.9900)
GO
INSERT [dbo].[OrderDetails] ([id], [sku], [order_id], [price], [quantity], [total]) VALUES (2, N'BU1111', 114, 11.9500, 1, 11.9500)
GO
INSERT [dbo].[OrderDetails] ([id], [sku], [order_id], [price], [quantity], [total]) VALUES (3, N'BU1032', 115, 19.9900, 1, 19.9900)
GO
SET IDENTITY_INSERT [dbo].[OrderDetails] OFF
GO



/****** Object:  Table [dbo].[Orders]    Script Date: 12/12/2022 7:07:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[orderId] [int] IDENTITY(1,1) NOT NULL,
	[dateBuy] [datetime] NOT NULL,
	[total] [money] NULL,
	[name] [nvarchar](32) NULL,
	[phone] [varchar](20) NULL,
	[address] [nvarchar](400) NULL,
	[username] [varchar](20) NULL,
	[status][nvarchar](50) NOT NULL
 CONSTRAINT [PK_OrderID] PRIMARY KEY CLUSTERED 
(
	[orderId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

SET IDENTITY_INSERT [dbo].[Orders] ON 
GO
INSERT [dbo].[Orders] ([orderId], [dateBuy], [total], [name], [phone], [address], [username], [status]) VALUES (114, CAST(N'2023-01-01T00:00:00.000' AS DateTime), 31.9400, N'Khoai Tay', N'0912345678', N'HN, Vietnam', N'khoaitay', N'Completed') 
GO
INSERT [dbo].[Orders] ([orderId], [dateBuy], [total], [name], [phone], [address], [username], [status]) VALUES (115, CAST(N'2023-01-01T00:00:00.000' AS DateTime), 19.9900, N'Khoai Tay', N'09349686986', N'HN, Vietnam', N'khoaitay', N'Completed')
GO
SET IDENTITY_INSERT [dbo].[Orders] OFF



/****** Object:  Table [dbo].[Product]    Script Date: 12/12/2022 7:07:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[sku] [varchar](10) NOT NULL,
	[name] [nvarchar](80) NOT NULL,
	[type] [char](12) NOT NULL,
	[description] [nvarchar](250) NULL,
	[quantity] [int] NOT NULL,
	[price] [money] NOT NULL,
	[status][int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[sku] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'BU1032', N'The Busy Executive''s Database Guide', N'business    ', N'An overview of available database systems with emphasis on common business applications. Illustrated.', 100, 19.9900, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'BU1111', N'Cooking with Computers: Surreptitious Balance Sheets', N'business    ', N'Helpful hints on how to use your electronic resources to the best advantage.', 90, 11.9500, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'BU2075', N'You Can Combat Computer Stress!', N'business    ', N'The latest medical and psychological techniques for living with the electronic office. Easy-to-understand explanations.', 35, 2.9900, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'BU7832', N'Straight Talk About Computers', N'business    ', N'Annotated analysis of what computers can do for you: a no-hype guide for the critical user.', 81, 19.9900, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'MC2222', N'Silicon Valley Gastronomic Treats', N'mod_cook    ', N'Favorite recipes for quick, easy, and elegant meals.', 89, 19.99, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'MC3021', N'The Gourmet Microwave', N'mod_cook    ', N'Traditional French gourmet recipes adapted for modern microwave cooking.', 94, 2.9900, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'MC3026', N'The Psychology of Computer Cooking', N'Technology    ', N'MVC2 and related core concepts', 90, 12.000,1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PC1035', N'But Is It User Friendly', N'Technology    ', N'A survey of software for the naive user, focusing on the ''friendliness'' of each.', 10, 22.9500, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PC8888', N'Secrets of Silicon Valley', N'Technology    ', N'Muckraking reporting on the world''s largest computer hardware and software manufacturers.', 2, 20.0000, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PC9999', N'Net Etiquette', N'Technology    ', N'A must-read for computer conferencing.', 5, 15.000, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PS1372', N'Computer Phobic AND Non-Phobic Individuals: Behavior Variations', N'psychology  ', N'A must for the specialist, this book examines the difference between those who hate and fear computers and those who don''t.', 10, 21.5900, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PS2091', N'Is Anger the Enemy?', N'psychology  ', N'Carefully researched study of the effects of strong emotions on the body. Metabolic charts included.', 100, 10.9500, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PS2106', N'Life Without Fear', N'psychology  ', N'New exercise, meditation, and nutritional techniques that can reduce the shock of daily interactions. Popular audience. Sample menus included, exercise video available separately.', 90, 7.0000, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PS3333', N'Prolonged Data Deprivation: Four Case Studies', N'psychology  ', N'What happens when the data runs dry?  Searching evaluations of information-shortage effects.', 35, 19.9900, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'PS7777', N'Emotional Security: A New Algorithm', N'psychology  ', N'Protecting yourself and your loved ones from undue emotional stress in the modern world. Use of computer and nutritional aids emphasized.', 81, 7.9900, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'TC3218', N'Onions, Leeks, and Garlic: Cooking Secrets of the Mediterranean', N'trad_cook   ', N'Profusely illustrated in color, this makes a wonderful gift book for a cuisine-oriented friend.', 89, 20.9500, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'TC4203', N'Fifty Years in Buckingham Palace Kitchens', N'trad_cook   ', N'More anecdotes from the Queen''s favorite cook describing life among English royalty. Recipes, techniques, tender vignettes.', 94, 11.9500, 1)
GO
INSERT [dbo].[Product] ([sku], [name], [type], [description], [quantity], [price], [status]) VALUES (N'TC7777', N'Sushi, Anyone?', N'trad_cook   ', N'Detailed instructions on how to make authentic Japanese sushi in your spare time.', 10, 14.9900, 1)
GO

/****** Object:  Table [dbo].[Registration]    Script Date: 12/12/2022 7:07:46 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](20) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[fullname] [nvarchar](100) NOT NULL,
	[isAdmin] [bit] NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([username], [password], [fullname], [isAdmin]) VALUES (N'admin', N'123', N'Administrator', 1)
GO
INSERT [dbo].[Account] ([username], [password], [fullname], [isAdmin]) VALUES (N'hieuld', N'123', N'Le Hieu', 0)
GO
INSERT [dbo].[Account] ([username], [password], [fullname], [isAdmin]) VALUES (N'khoaitay', N'123', N'Khoai Tay', 0)
GO



ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Order] FOREIGN KEY([order_id])
REFERENCES [dbo].[Orders] ([orderId])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Order]
GO
ALTER TABLE [dbo].[OrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_OrderDetails_Product] FOREIGN KEY([sku])
REFERENCES [dbo].[Product] ([sku])
GO
ALTER TABLE [dbo].[OrderDetails] CHECK CONSTRAINT [FK_OrderDetails_Product]
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD  CONSTRAINT [FK_Orders_Account] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Orders] CHECK CONSTRAINT [FK_Orders_Account]
GO
USE [master]
GO
ALTER DATABASE [BOOKSTORE] SET  READ_WRITE 
GO

