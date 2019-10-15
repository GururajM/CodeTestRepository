# CodeTestRepository


# codeTest

codeTest is a RESTful web application written using Spring MVC framework.

The application reads the data on actions that have happened during the course of games in several league and presents several aggregate statistics related to the leagues and matches.

The statistics are exposed as set of endpoints that can be accessed by HTTP GET request from a REST Client.


# Features

The statistics provided are:
 1. Team statistics of league so far: Team name, points, games won, games drawn, games lost, goals scored, goals conceeded
 
 2. Matchday resutls from a league and date: home team, away team, home team number of goals, away team number of goals, home teams goal scorers, away teams goal scorers
 
 3. Individual statistics of goals/assists for players: player name, number of goals scored/player name, number of assists made
 
 
 # Calling the APIs
 
 1. Team statistics:
	example 1 (Premier League): http://localhost:(port)/codeTest/entry/v1/leaguetable/PremierLeague/
	example 2 (Eredivisie): http://localhost:(port)/codeTest/entry/v1/leaguetable/Eredivisie/	
	
 2. Matchday resutls:	
	example 1 (Premier League, 22-Sep-19): http://localhost:(port)/codeTest/entry/v1/matchDay/PremierLeague/22-Sep-19/
	example 2 (Premier League, 15-Sep-19): http://localhost:(port)/codeTest/entry/v1/matchDay/PremierLeague/15-Sep-19/
	example 3 (Eredivisie, 3-Aug-19): http://localhost:(port)/codeTest/entry/v1/matchDay/Eredivisie/3-Aug-19/
	
 3. Individual statistics:
	example 1 (Premier League, goal scorers): http://localhost:(port)/codeTest/entry/v1/individual/PremierLeague/goalscorers
	example 2 (Premier League, assists makers): http://localhost:(port)/codeTest/entry/v1/individual/PremierLeague/assisters	
	example 3 (Premier League, goal scorers): http://localhost:(port)/codeTest/entry/v1/individual/Eredivisie/goalscorers
	example 4 (Premier League, assists makers): http://localhost:(port)/codeTest/entry/v1/individual/Eredivisie/assisters	
