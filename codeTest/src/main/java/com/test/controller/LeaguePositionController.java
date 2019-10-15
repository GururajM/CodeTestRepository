package com.test.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.model.TeamStats;

/**
* Program to represent controller that handles requests
* for team details for the specified league.
*
* @author  Gururaj Maddodi
* @version 1.0
* @since   2019-10-14
*/
@Controller
@RequestMapping("/v1/leaguetable/")
public class LeaguePositionController {

	//Method for handling request for a specified league
	@RequestMapping(value = "/{league}/", method = RequestMethod.GET)
	public @ResponseBody TeamStats[] getLeaguePosition(@PathVariable("league") String league) {
		
		try {
			LeaguePositionController lpc = new LeaguePositionController();
			File file = lpc.getFileFromResources();
			
			Map<String, TeamStats> matches = getMatches(file, league);
			//TeamStats[] values = (TeamStats[]) matches.values().toArray();
			TeamStats[] results = new TeamStats[matches.size()];
			int i=0;
			for(Map.Entry<String, TeamStats> pair: matches.entrySet()) {
				results[i] = pair.getValue();
				i++;
			}
			return results;
		}catch(Exception e) {
			return null;
		}
	}
	
	
	//Get the file from the resources
    private File getFileFromResources() {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource("MatchActions_PremierLeague20182019_20190927.csv");
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
    
    //Method to get team details from league from the data
	private static Map<String, TeamStats> getMatches(File file, String league) {
		if(league.equals("PremierLeague")) {
			league = "Premier League";
		}
		List<List<String>> records = new ArrayList<List<String>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			try {
				while ((line = br.readLine()) != null) {
					String[] values = line.split(",");
					records.add(Arrays.asList(values));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, List<String>> map2 = new HashMap<String, List<String>>();
		for (List<String> num : records) { 	
			if(num.contains(league)) {					//Get only one records per match 
				map2.put(num.get(2), num);
			}
		}
		
		Map<String, TeamStats> test = new HashMap<String, TeamStats>();
		for (Map.Entry<String, List<String>> pair: map2.entrySet()) { 	
			
			if(test.get(pair.getValue().get(3)) == null) {				//If the team is home team and does not exists in the map
				TeamStats temp = new TeamStats(pair.getValue().get(3), pair.getValue().get(1), 0, 
						(Integer.parseInt(pair.getValue().get(5)) > Integer.parseInt(pair.getValue().get(6)) ? 1:0),  //match won, if home goals > away goals
						(Integer.parseInt(pair.getValue().get(5)) == Integer.parseInt(pair.getValue().get(6)) ? 1:0), //match drawn, if home goals == away goals
						(Integer.parseInt(pair.getValue().get(5)) < Integer.parseInt(pair.getValue().get(6)) ? 1:0),  //match lost, if home goals < away goals
						Integer.parseInt(pair.getValue().get(5)), Integer.parseInt(pair.getValue().get(6)));
		 		test.put(pair.getValue().get(3), temp );
			}
			else {														//If the team is home team and already exists in the map
				TeamStats temp = test.get(pair.getValue().get(3));
				temp.setGamesWon(temp.getGamesWon() + (Integer.parseInt(pair.getValue().get(5)) > Integer.parseInt(pair.getValue().get(6)) ? 1:0));
				temp.setGamesDrawn(temp.getGamesDrawn() + (Integer.parseInt(pair.getValue().get(5)) == Integer.parseInt(pair.getValue().get(6)) ? 1:0));
				temp.setGamesLost(temp.getGamesLost() + (Integer.parseInt(pair.getValue().get(5)) < Integer.parseInt(pair.getValue().get(6)) ? 1:0));
				temp.setGoalScored(temp.getGoalScored() + Integer.parseInt(pair.getValue().get(5)));
				temp.setGoalConceeded(temp.getGoalConceeded() + Integer.parseInt(pair.getValue().get(6)));
		 		test.put(pair.getValue().get(3), temp );
			}
		 
			if(test.get(pair.getValue().get(4)) == null) {				//If the team is away team and does not exists in the map
				TeamStats temp = new TeamStats(pair.getValue().get(4), pair.getValue().get(1), 0, 
						(Integer.parseInt(pair.getValue().get(6)) > Integer.parseInt(pair.getValue().get(5)) ? 1:0),  //match won, if away goals > home goals
						(Integer.parseInt(pair.getValue().get(6)) == Integer.parseInt(pair.getValue().get(5)) ? 1:0), //match drawn, if away goals == home goals
						(Integer.parseInt(pair.getValue().get(6)) < Integer.parseInt(pair.getValue().get(5)) ? 1:0),  //match lost, if away goals < home goals
						Integer.parseInt(pair.getValue().get(6)), Integer.parseInt(pair.getValue().get(5)));
		 		test.put(pair.getValue().get(4), temp);	
			}
			
			else {
				TeamStats temp = test.get(pair.getValue().get(4));
				temp.setGamesWon(temp.getGamesWon() + (Integer.parseInt(pair.getValue().get(6)) > Integer.parseInt(pair.getValue().get(5)) ? 1:0));
				temp.setGamesDrawn(temp.getGamesDrawn() + (Integer.parseInt(pair.getValue().get(6)) == Integer.parseInt(pair.getValue().get(5)) ? 1:0));
				temp.setGamesLost(temp.getGamesLost() + (Integer.parseInt(pair.getValue().get(6)) < Integer.parseInt(pair.getValue().get(5)) ? 1:0));
				temp.setGoalScored(temp.getGoalScored() + Integer.parseInt(pair.getValue().get(6)));
				temp.setGoalConceeded(temp.getGoalConceeded() + Integer.parseInt(pair.getValue().get(5)));
		 		test.put(pair.getValue().get(4), temp );
			}
		}

		for (Map.Entry<String, TeamStats> pair: test.entrySet()) {			//Calculate points from games won and drawn
			TeamStats temp = pair.getValue();
			temp.setPoints(temp.getGamesWon()*3+temp.getGamesDrawn());		
			test.put(pair.getKey(), temp);
		}
		return test;
	}
}
