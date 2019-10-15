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

import com.test.model.Assisters;
import com.test.model.GoalScorers;
import com.test.model.MatchScore;

/**
* Program to represent controller that handles requests
* for individual stats like goal scorers and assisters.
*
* @author  Gururaj Maddodi
* @version 1.0
* @since   2019-10-14
*/
@Controller
@RequestMapping("/v1/individual")
public class IndividualStatsController {

	//Method for handling request for getting goal scorers and goals scored
	@RequestMapping(value = "/{league}/goalscorers", method = RequestMethod.GET)
	public @ResponseBody GoalScorers[] getGoalScorers(@PathVariable("league") String league) {
		
		//Replace league from "PremierLeague" from URL to "Premier League"
		if(league.equals("PremierLeague")) {
			league="Premier League";
		}
		
		try {
			IndividualStatsController isc = new IndividualStatsController();
			File file = isc.getFileFromResources();
			Map<String, GoalScorers> matches = getGoalEntries(file, league);

			GoalScorers[] results = new GoalScorers[matches.size()];
			int i=0;
			for(Map.Entry<String, GoalScorers> pair: matches.entrySet()) {
				results[i] = pair.getValue();
				i++;
			}
			return results;					
		}catch(Exception e) {
			//handle exception
			return null;
		}
	}
	
	//Method for handling request for getting assists makers and assists made
	@RequestMapping(value = "/{league}/assisters", method = RequestMethod.GET)
	public @ResponseBody Assisters[] getAssisters(@PathVariable("league") String league) {
		
		if(league.equals("PremierLeague")) {
			league="Premier League";
		}
		
		try {
			IndividualStatsController isc = new IndividualStatsController();
			File file = isc.getFileFromResources();
			Map<String, Assisters> matches = getAssistEntries(file, league);

			Assisters[] results = new Assisters[matches.size()];
			int i=0;
			for(Map.Entry<String, Assisters> pair: matches.entrySet()) {
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
	
    //Method to get goal scorers from the data
	private static Map<String, GoalScorers> getGoalEntries(File file, String league) {
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
		
		Map<String, GoalScorers> map = new HashMap<String, GoalScorers>();	//Map storing players vs. their goal scored stats
		for (List<String> num : records) { 
			if(num.contains(league) && num.contains("Goal")) {		//Check if the record is from specified league and a goal action
				if(map.get(num.get(16)) == null) {					//Check if the a goal scorer is already added
					map.put(num.get(16), new GoalScorers(num.get(16), 1));
				}
				else {												// Goal scorer already added, then retrieve the value and update
					GoalScorers temp = map.get(num.get(16));
					temp.setNumGoals(temp.getNumGoals()+1);
					map.put(num.get(16), temp);
				}
			}
		}
		return map;
	}

    //Method to get assist makers from the data
	private static Map<String, Assisters> getAssistEntries(File file, String league) {
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
	
		Map<String, Assisters> map = new HashMap<String, Assisters>();		//Map storing players vs. their assists made stats
		for (List<String> num : records) { 
			if(num.contains(league) && num.contains("Assist")) {			//Check if the record is from specified league and contains assist
				if(map.get(num.get(22)) == null) {							//Check if the assister is already added
					map.put(num.get(22), new Assisters(num.get(22), 1));
				}
				else {														// assister already added, then retrieve the value and update
					Assisters temp = map.get(num.get(22));
					temp.setNumAssists(temp.getNumAssists()+1);
					map.put(num.get(22), temp);
				}
			}
		}
	return map;
	}
}
