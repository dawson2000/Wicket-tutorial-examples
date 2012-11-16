package org.wicketTutorial;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.wicket.Component;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.extensions.markup.html.repeater.tree.NestedTree;
import org.apache.wicket.extensions.markup.html.repeater.tree.content.CheckedFolder;
import org.apache.wicket.extensions.markup.html.repeater.tree.theme.WindowsTheme;
import org.apache.wicket.extensions.markup.html.repeater.util.TreeModelProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		super(parameters);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Cities of Europe");
		
		addNodes(addNode(root, "Italy"), "Rome", "Venice", "Milan", "Florence");
		addNodes(addNode(root, "Germany"), "Stuttgart", "Munich", "Berlin", "Dusseldorf", "Dresden");
		addNodes(addNode(root, "France"), "Paris ", "Toulouse", "Strasbourg", "Bordeaux", "Lyon");
		
		DefaultTreeModel treeModel = new DefaultTreeModel(root);
		TreeModelProvider<DefaultMutableTreeNode> modelProvider = new TreeModelProvider<DefaultMutableTreeNode>(treeModel) {
			
			@Override
			public IModel<DefaultMutableTreeNode> model(DefaultMutableTreeNode object) {
				return Model.of(object);
			}
		};
		
		NestedTree<DefaultMutableTreeNode> tree = new NestedTree<DefaultMutableTreeNode>("tree", modelProvider)
	    {
	        private static final long serialVersionUID = 1L;
	        
	        @Override
	        protected Component newContentComponent(String id, IModel<DefaultMutableTreeNode> model)
	        {
	            return new CheckedFolder<DefaultMutableTreeNode>(id, this, model);
	        }
	    };
	    //select Windows theme
	    tree.add(new WindowsTheme());
	    
	    add(tree);
    }
    
    /**
     * Creates a child node for every string in input 
     * @param parent
     * @param childrenNode
     */
    public static void addNodes(DefaultMutableTreeNode parent, String... childrenNode){
    	for (int i = 0; i < childrenNode.length; i++) {
			addNode(parent, childrenNode[i]);
		}
    }
    
    /**
     * Creates a child node for the string in input 
     * @param parent
     * @param childNode
     * @return
     */
    public static DefaultMutableTreeNode addNode(DefaultMutableTreeNode parent, String childNode){
    	DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(childNode);    	
    	parent.add(newNode);
    	
    	return newNode;
    }
}
