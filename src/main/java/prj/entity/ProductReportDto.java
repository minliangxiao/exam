package prj.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductReportDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1287056448363559805L;
	private List<String> label1 = new ArrayList<String>();
	private List<String> label2 = new ArrayList<String>();
	private List<Integer> data1 = new ArrayList<Integer>();
	private List<Integer> data2 = new ArrayList<Integer>();
	private List<Double> amount1 = new ArrayList<Double>();
	private List<Double> amount2 = new ArrayList<Double>();
	public List<String> getLabel1() {
		return label1;
	}
	public void setLabel1(List<String> label1) {
		this.label1 = label1;
	}
	public List<String> getLabel2() {
		return label2;
	}
	public void setLabel2(List<String> label2) {
		this.label2 = label2;
	}
	public List<Integer> getData1() {
		return data1;
	}
	public void setData1(List<Integer> data1) {
		this.data1 = data1;
	}
	public List<Integer> getData2() {
		return data2;
	}
	public void setData2(List<Integer> data2) {
		this.data2 = data2;
	}
	public List<Double> getAmount1() {
		return amount1;
	}
	public void setAmount1(List<Double> amount1) {
		this.amount1 = amount1;
	}	
	public List<Double> getAmount2() {
		return amount2;
	}
	public void setAmount2(List<Double> amount2) {
		this.amount2 = amount2;
	}	
	
}
