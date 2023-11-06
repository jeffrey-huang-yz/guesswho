import java.sql.Array;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel; 

public class Character extends JLabel {

	private String name;
	private String hairColour;
	private String clothingColour;
	
	private Boolean wearHat;
	private Boolean hasClaw;
	private Boolean hasVehicle;
	private Boolean hasBeard;
	private Boolean wearsEarrings;
	private Boolean wearsHelmet;
	private Boolean wearsTie;
	private Boolean hasBeak;
	private Boolean hasTail;
	private Boolean hasGuitar;
	private Boolean hasScales;
	private String fileName;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHairColour() {
		return hairColour;
	}
	public void setHairColour(String hairColour) {
		this.hairColour = hairColour;
	}
	public String getClothingColour() {
		return clothingColour;
	}
	public void setClothingColour(String clothingColour) {
		this.clothingColour = clothingColour;
	}
	public Boolean getWearHat() {
		return wearHat;
	}
	public void setWearHat(Boolean wearHat) {
		this.wearHat = wearHat;
	}
	public Boolean getHasClaw() {
		return hasClaw;
	}
	public void setHasClaw(Boolean hasClaw) {
		this.hasClaw = hasClaw;
	}
	public Boolean getHasVehicle() {
		return hasVehicle;
	}
	public void setHasVehicle(Boolean hasVehicle) {
		this.hasVehicle = hasVehicle;
	}
	public Boolean getHasBeard() {
		return hasBeard;
	}
	public void setHasBeard(Boolean hasBeard) {
		this.hasBeard = hasBeard;
	}
	public Boolean getWearsEarrings() {
		return wearsEarrings;
	}
	public void setWearsEarrings(Boolean wearsEarrings) {
		this.wearsEarrings = wearsEarrings;
	}
	public Boolean getWearsHelmet() {
		return wearsHelmet;
	}
	public void setWearsHelmet(Boolean wearsHelmet) {
		this.wearsHelmet = wearsHelmet;
	}
	public Boolean getWearsTie() {
		return wearsTie;
	}
	public void setWearsTie(Boolean wearsTie) {
		this.wearsTie = wearsTie;
	}
	public Boolean getHasBeak() {
		return hasBeak;
	}
	public void setHasBeak(Boolean hasBeak) {
		this.hasBeak = hasBeak;
	}
	public Boolean getHasTail() {
		return hasTail;
	}
	public void setHasTail(Boolean hasTail) {
		this.hasTail = hasTail;
	}
	public Boolean getHasGuitar() {
		return hasGuitar;
	}
	public void setHasGuitar(Boolean hasGuitar) {
		this.hasGuitar = hasGuitar;
	}
	public Boolean getHasScales() {
		return hasScales;
	}
	public void setHasScales(Boolean hasScales) {
		this.hasScales = hasScales;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = "images/" + fileName + ".jpeg"; 
	}
	@Override
	public String toString() {
		return "Character [name=" + name + ", hairColour=" + hairColour + ", clothingColour=" + clothingColour
				+ ", wearHat=" + wearHat + ", hasClaw=" + hasClaw + ", hasVehicle=" + hasVehicle + ", hasBeard="
				+ hasBeard + ", wearsEarrings=" + wearsEarrings + ", wearsHelmet=" + wearsHelmet + ", wearsTie="
				+ wearsTie + ", hasBeak=" + hasBeak + ", hasTail=" + hasTail + ", hasGuitar=" + hasGuitar
				+ ", hasScales=" + hasScales + ", fileName=" + fileName + "]";
	}
	
	
	
}
