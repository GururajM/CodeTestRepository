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

import com.test.model.MatchScore;

/**
* Program to represent controller that handles requests
* for match day results.
*
* @author  Gururaj Maddodi
* @version 1.0
* @since   2019-10-14
*/
@Controller
@RequestMapping("/v1/matchDay")
public class MatchDayResulltsController {

	//Method for handling request for a specified league and match day
	@RequestMapping(value = "/{league}/{matchDate}", method = RequestMethod.GET)
	public @ResponseBody MatchScore[] getLeagueTable(@PathVariable("league") String league, @PathVariable("matchDate") String matchDate) {
		// TODO Auto-generated method stub
		if(league.equals("PremierLeague")) {
			league="Premier League";
		}
		
		try {
			MatchDayResulltsController mrc = new MatchDayResulltsController();
			File file = mrc.getFileFromResources();
			
			Map<String, MatchScore> matches = getMatches(file, league, matchDate);
			
			MatchScore[] results = new MatchScore[matches.size()];
			int i=0;
			for(Map.Entry<String, MatchScore> pair: matches.entrySet()) {
				results[i] = pair.getValue();
				i++;
			}
			return results;			
		}catch(Exception e) {
			//handle exception
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
	
    
    //Method to get matches from league and match day from the data
	private static Map<String, MatchScore> getMatches(File file, String league, String matchDate) {
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
		
		Map<String, MatchScore> map = new HashMap<String, MatchScore>();
		for (List<String> num : records) { 
			if(num.contains(matchDate) && num.contains(league) && num.contains("Goal")) {		//Eliminate all records not from not specified league, match day, and non-goal related 
				if(map.get(num.get(2)) == null) {					//If match id not added already
					if(num.get(14).equals(num.get(3))) {			//If goal scorer is from home team, add to homeScorers list
						//List<String> homeGoalScorers = new 
						MatchScore temp = new MatchScore(num.get(3), num.get(4), Integer.parseInt(num.get(5)), Integer.parseInt(num.get(5)),
										new ArrayList<String>(Arrays.asList(num.get(16))), new ArrayList<String>());	
						map.put(num.get(2), temp);	
					}
					else if(num.get(14).equals(num.get(4))) {		//If goal scorer is from away team, add to awayScorers list
						MatchScore temp = new MatchScore(num.get(3), num.get(4), Integer.parseInt(num.get(5)), Integer.parseInt(num.get(5)),
								new ArrayList<String>(), new ArrayList<String>(Arrays.asList(num.get(16))));		
						map.put(num.get(2), temp);	
					}							
				}
				else {												//Match id added already
					if(num.get(14).equals(num.get(3))) {			//If goal scorer is from home team, add to existing homeScorers list
						MatchScore temp = map.get(num.get(2));
						List<String> tempList = temp.getHomeGoalScores();
						tempList.add(num.get(16));
						temp.setHomeGoalScores(tempList);
						map.put(num.get(2), temp);			
					}
					else if(num.get(14).equals(num.get(4))) {		//If goal scorer is from away team, add to existing awayScorers list
						MatchScore temp = map.get(num.get(2));
						List<String> tempList = temp.getAwayGoalScores();
						tempList.add(num.get(16));
						temp.setAwayGoalScores(tempList);
						map.put(num.get(2), temp);	
					}
							
				}
			}
		}
		return map;
	}
}
