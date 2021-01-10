package org.sontana.tools;

import java.util.HashMap;

public class StateMachine
{

	// Make statemachines generic instead of String based?
	
	class Node
	{
		private Node(String name) { nodeName = name; }
		private String nodeName;
		private Node nextNode = root;
	}
	
	
	private static HashMap<String, StateMachine> stateMachines = new HashMap<>();
	
	private StateMachine(String pName, String pRoot)
	{
		name = pName;
		root = new Node(pRoot);
		cur = root;
		
		nodes.put(pRoot, root);
	}
	
	public static StateMachine getStateMachine(String pName)
	{
		if(stateMachines.containsKey(pName))
		{
			return stateMachines.get(pName);
		}
		
		Console.logError("StateMachine " + pName + " doesn't exist.");
		
		return null;
	}
	
	public static StateMachine makeStateMachine(String pName, String pRoot)
	{
		if(stateMachines.containsKey(pName))
		{
			Console.logWarning("StateMachine " + pName + " already exists.");
			return stateMachines.get(pName);
		}
		
		return stateMachines.put(pName, new StateMachine(pName, pRoot));
	}
	
	
	private String name;
	
	private Node root;
	
	private Node cur;
	
	private HashMap<String, Node> nodes = new HashMap<>();
	
	
	public void reset()
	{
		cur = root;
	}
	
	public void setState(String pNodeName)
	{
		if(nodes.containsKey(pNodeName))
		{
			cur = nodes.get(pNodeName);
		}
		else
		{
			Console.logWarning("StateMachine " + name + " does contain the state " + pNodeName);
		}
	}
	
	public String getState()
	{
		return cur.nodeName;
	}
	
	public void addState(String pState)
	{
		nodes.putIfAbsent(pState, new Node(pState));
	}
	
	public void link(String pFrom, String pTo)
	{
		if(!nodes.containsKey(pFrom) || !nodes.containsKey(pTo))
		{
			Console.logError("StateMachine " + name + ": Could not link " + pFrom + " and " + pTo);
			return;
		}
		
		nodes.get(pFrom).nextNode = nodes.get(pTo);
	}
	
	public String advanceState()
	{
		cur = cur.nextNode;
		
		return cur.nodeName;
	}
	
	public void debugPrint()
	{
		for(Node n : nodes.values())
		{
			System.out.println(n.nodeName + " : " + n.nextNode);
		}
	}
	
}

