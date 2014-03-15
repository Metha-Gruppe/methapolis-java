package refactored;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class Tile extends MapObject {
	
	// ATTRIBUTES
	
	// TODO: reference to building
//	Building building;
	
	// CONSTRUCTORS
	public Tile()	{
		
	}
	
	public Tile(int id) {
		this.id = id;
		this.attributes = new HashMap<String, String>();
		this.images = new ArrayList<MapImage>();
	}
	
	public static Tile newTileFromID(int id)	{
		// TODO: to be implemented...
		return null;
	}
	
	// METHODS
	// STATIC
	public static Tile parse(int id, String inStr, Properties tilesRc) {
		Tile tile = new Tile(id);
		tile.load(inStr, tilesRc);
		return tile;
	}

//	public String getAttribute(String key) {
//		return attributes.get(key);
//	}
//
//	public boolean getBooleanAttribute(String key) {
//		String v = getAttribute(key);
//		return (v != null && v.equals("true"));
//	}

//	public static class BuildingInfo {
//		int width;
//		int height;
//		short[] members;
//		// TODO: add player id here
//	}

//	public BuildingInfo getBuildingInfo() {
//		return buildingInfo;
//	}

//	private void resolveBuildingInfo(Map<String, TileSpec> tileMap) {
//		String tmp = getAttribute("building");
//		if(tmp == null) {
//			return;
//		}
//
//		BuildingInfo bi = new BuildingInfo();
//
//		String[] p2 = tmp.split("x");
//		bi.width = Integer.parseInt(p2[0]);
//		bi.height = Integer.parseInt(p2[1]);
//
//		bi.members = new short[bi.width * bi.height];
//		int startTile = tileNumber;
//		if(bi.width >= 3) {
//			startTile--;
//		}
//		if(bi.height >= 3) {
//			startTile -= bi.width;
//		}
//
//		for(int row = 0; row < bi.height; row++) {
//			for(int col = 0; col < bi.width; col++) {
//				bi.members[row * bi.width + col] = (short) startTile;
//				startTile++;
//			}
//		}
//
//		this.buildingInfo = bi;
//	}

//	public CityDimension getBuildingSize() {
//		if(buildingInfo != null) {
//			return new CityDimension(buildingInfo.width, buildingInfo.height);
//		}
//		else {
//			return null;
//		}
//	}
//
//	public int getDescriptionNumber() {
//		String v = getAttribute("description");
//		if(v != null && v.startsWith("#")) {
//			return Integer.parseInt(v.substring(1));
//		}
//		if(owner != null) {
//			return owner.getDescriptionNumber();
//		}
//		return -1;
//	}

//	public String[] getImages() {
//		return images.toArray(new String[0]);
//	}

//	public int getPollutionValue() {
//		String v = getAttribute("pollution");
//		if(v != null) {
//			return Integer.parseInt(v);
//		}
//		else if(owner != null) {
//			// pollution inherits from building tile
//			return owner.getPollutionValue();
//		}
//		else {
//			return 0;
//		}
//	}

//	public int getPopulation() {
//		String v = getAttribute("population");
//		if(v != null) {
//			return Integer.parseInt(v);
//		}
//		else {
//			return 0;
//		}
//	}

	// TODO: rewrite this
	protected void load(String inStr, Properties tilesRc) {
		Scanner in = new Scanner(inStr);

//		while(in.hasMore()) {
//
//			if(in.peekChar() == '(') {
//				in.eatChar('(');
//				String k = in.readAttributeKey();
//				String v = "true";
//				if(in.peekChar() == '=') {
//					in.eatChar('=');
//					v = in.readAttributeValue();
//				}
//				in.eatChar(')');
//
//				if(!attributes.containsKey(k)) {
//					// TODO:
//					System.out.println(k + ", " + v);
//					
//					attributes.put(k, v);
//					String sup = tilesRc.getProperty(k);
//					if(sup != null) {
//						load(sup, tilesRc);
//					}
//				}
//				else {
//					attributes.put(k, v);
//				}
//			}
//
//			else if(in.peekChar() == '|' || in.peekChar() == ',') {
//				in.eatChar(in.peekChar());
//			}
//
//			else {
//				String v = in.readImageSpec();
//				images.add(v);
//			}
//		}

//		this.canBulldoze = getBooleanAttribute("bulldozable");
//		this.setBulldozable(bulldozable);
//		this.canBurn = !getBooleanAttribute("noburn");
//		this.canConduct = getBooleanAttribute("conducts");
//		this.overWater = getBooleanAttribute("overwater");
//		this.zone = getBooleanAttribute("zone");
	}

	// TODO: also rewrite this
	static class Scanner {
		String str;
		int off = 0;

		Scanner(String str) {
			this.str = str;
		}

		private void skipWhitespace() {
			while(off < str.length() && Character.isWhitespace(str.charAt(off))) {
				off++;
			}
		}

		public int peekChar() {
			skipWhitespace();
			if(off < str.length()) {
				return str.charAt(off);
			}
			else {
				return -1;
			}
		}

		public void eatChar(int ch) {
			skipWhitespace();
			assert str.charAt(off) == ch;
			off++;
		}

		public String readAttributeKey() {
			skipWhitespace();

			int start = off;
			while(off < str.length() && (str.charAt(off) == '-' || Character.isLetterOrDigit(str.charAt(off)))) {
				off++;
			}

			if(off != start) {
				return str.substring(start, off);
			}
			else {
				return null;
			}
		}

//		public String readAttributeValue() {
//			return readString();
//		}
//
//		public String readImageSpec() {
//			return readString();
//		}
//
//		protected String readString() {
//			skipWhitespace();
//
//			int endQuote = 0; // any whitespace or certain punctuation
//			if(peekChar() == '"') {
//				off++;
//				endQuote = '"';
//			}
//
//			int start = off;
//			while(off < str.length()) {
//				int c = str.charAt(off);
//				if(c == endQuote) {
//					int end = off;
//					off++;
//					return str.substring(start, end);
//				}
//				else if(endQuote == 0 && (Character.isWhitespace(c) || c == ')' || c == '|')) {
//					int end = off;
//					return str.substring(start, end);
//				}
//				off++;
//			}
//			return str.substring(start);
//		}
//
//		public boolean hasMore() {
//			return peekChar() != -1;
//		}
//	}

//	public String toString() {
//		return "{tile#" + id + "}";
//	}

	// TODO: what does this do?
//	void resolveReferences(Map<String, TileSpec> tileMap) {
//		String tmp = this.getAttribute("becomes");
//		if(tmp != null) {
//			this.animNext = tileMap.get(tmp);
//		}
//		tmp = this.getAttribute("onpower");
//		if(tmp != null) {
//			this.onPower = tileMap.get(tmp);
//		}
//		tmp = this.getAttribute("onshutdown");
//		if(tmp != null) {
//			this.onShutdown = tileMap.get(tmp);
//		}
//		tmp = this.getAttribute("building-part");
//		if(tmp != null) {
//			this.handleBuildingPart(tmp, tileMap);
//		}
//
//		resolveBuildingInfo(tileMap);
//	}

		// TODO: what does this do?
//	private void handleBuildingPart(String text, Map<String, TileSpec> tileMap) {
//		String[] parts = text.split(",");
//		if(parts.length != 3) {
//			throw new Error("Invalid building-part specification");
//		}
//
//		this.owner = tileMap.get(parts[0]);
//		this.ownerOffsetX = Integer.parseInt(parts[1]);
//		this.ownerOffsetY = Integer.parseInt(parts[2]);
//
//		assert this.owner != null;
//		assert this.ownerOffsetX != 0 || this.ownerOffsetY != 0;
//	}

	// TODO: what does this do?
//	public static String[] generateTileNames(Properties recipe) {
//		int ntiles = recipe.size();
//		String[] tileNames = new String[ntiles];
//		ntiles = 0;
//		for(int i = 0; recipe.containsKey(Integer.toString(i)); i++) {
//			tileNames[ntiles++] = Integer.toString(i);
//		}
//		int naturalNumberTiles = ntiles;
//
//		for(Object n_obj : recipe.keySet()) {
//			String n = (String) n_obj;
//			if(n.matches("^\\d+$")) {
//				int x = Integer.parseInt(n);
//				if(x >= 0 && x < naturalNumberTiles) {
//					assert tileNames[x].equals(n);
//					continue;
//				}
//			}
//			assert ntiles < tileNames.length;
//			tileNames[ntiles++] = n;
//		}
//		assert ntiles == tileNames.length;
//		return tileNames;
	}
	
	
	// GETTERS & SETTERS
}
