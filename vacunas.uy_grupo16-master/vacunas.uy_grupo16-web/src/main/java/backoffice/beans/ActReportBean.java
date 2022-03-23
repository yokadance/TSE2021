package backoffice.beans;

import DTO.*;
import IController.*;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import org.omnifaces.util.Faces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.component.export.PDFOptions;
import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;
import org.primefaces.model.charts.pie.PieChartOptions;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.List;


@Named("ActReportBean")
@ViewScoped
public class ActReportBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  private List<DTVaccinationActView> listDtActView;

  private List<DTVaccinationAct> listDtVaccinationActs;

  private List<DTVaccinationAct> selectedVaccinationActs;

  private List<DTVaccinationAct> filteredVaccinationActs;

  private Exporter<DataTable> textExporter;

  private PDFOptions pdfOpt;

  private HorizontalBarChartModel hbarModel;

  private PieChartModel pieModel;


  Filter<DTVaccinationAct> filter = new Filter<>(new DTVaccinationAct());

  @EJB
  IVaccinationActController iVaccinationActController;
  @EJB
  IVaccinationPostController iVaccinationPostController;
  @EJB
  IPackageController iPackageController;
  @EJB
  ICitizenController iCitizenController;

  public ActReportBean() {}

  public void init() throws ParseException {
    customizationOptions();
    createHorizontalBarModel();
    createPieModel();
    if (Faces.isAjaxRequest()) {

      return;
    }

  }

  public void customizationOptions() {

    pdfOpt = new PDFOptions();
    pdfOpt.setFacetBgColor("#b7b7b7");
    pdfOpt.setFacetFontColor("#b7b7b7");
    pdfOpt.setFacetFontStyle("BOLD");
    pdfOpt.setCellFontSize("1");
    pdfOpt.setFontName("Courier");
  }

  public PDFOptions getPdfOpt() {
    return pdfOpt;
  }

  public void setPdfOpt(PDFOptions pdfOpt) {
    this.pdfOpt = pdfOpt;
  }

  public void preProcessPDF(Object document) throws DocumentException, IOException {
    Document pdf = (Document) document;

    HeaderFooter footer = new HeaderFooter(
            new Phrase("Página ", new Font()), true);
    footer.setBorder(Rectangle.NO_BORDER);
    footer.setAlignment(Element.ALIGN_RIGHT);
    pdf.setFooter(footer);

    pdf.open();
    pdf.setPageSize(PageSize.A4);
    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

    String imageUrl = this.getClass().getClassLoader().getResource("/logo.png").toExternalForm();
    Image image = Image.getInstance(new URL(imageUrl));
    image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
    image.setAlignment(Image.MIDDLE);

    float x = (PageSize.A4.getWidth() - image.getScaledWidth()) / 2;
    float y = (PageSize.A4.getHeight() - image.getScaledHeight()) / 2;
    image.setAbsolutePosition(0, PageSize.A4.getHeight() - image.getScaledHeight());
    pdf.add(image);

    Date today = new Date();
    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
    String stringDate= DateFor.format(today);
    Paragraph para = new Paragraph(stringDate);
    para.setAlignment(Element.ALIGN_RIGHT);
    pdf.add(para);

    PdfPTable table = new PdfPTable(7); // 3 columns.
    table.setWidthPercentage(100); //Width 100%
    table.setSpacingBefore(80f); //Space before table
    table.setSpacingAfter(80f); //Space after table


    //Set Column widths
    float[] columnWidths = {1f, 1f, 1f, 1f, 1f, 1.5f, 1f};
    table.setWidths(columnWidths);

    PdfPCell cell1 = new PdfPCell(new Paragraph("Cedula"));
    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell2 = new PdfPCell(new Paragraph("Nombre"));
    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell3 = new PdfPCell(new Paragraph("Apellido"));
    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell4 = new PdfPCell(new Paragraph("Centro"));
    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell5 = new PdfPCell(new Paragraph("Vacuna"));
    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell6 = new PdfPCell(new Paragraph("Enfermedad"));
    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell7 = new PdfPCell(new Paragraph("Fecha"));
    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
    table.addCell(cell5);
    table.addCell(cell6);
    table.addCell(cell7);


    List<DTVaccinationActView> listVacAct = getReports();
    if(listVacAct != null){
        for (DTVaccinationActView dtActView : listVacAct) {

          PdfPCell cellD1 = new PdfPCell(new Paragraph(dtActView.getPersonCi()));
          cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cellD1);

          PdfPCell cellD2 = new PdfPCell(new Paragraph(dtActView.getPersonName()));
          cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cellD2);

          PdfPCell cellD3 = new PdfPCell(new Paragraph(dtActView.getPersonLastName()));
          cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cellD3);

          PdfPCell cellD4 = new PdfPCell(new Paragraph(dtActView.getVaccinationCenter()));
          cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cellD4);

          PdfPCell cellD5 = new PdfPCell(new Paragraph(dtActView.getVaccine()));
          cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cellD5);

          PdfPCell cellD6 = new PdfPCell(new Paragraph(dtActView.getDisease()));
          cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cellD6);

          String[] part = dtActView.getVaccinationActDate().split(" ");
          PdfPCell cellD7 = new PdfPCell(new Paragraph(part[0]));
          cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
          cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
          table.addCell(cellD7);
        }
    }

    pdf.add(table);

    String imageUrl2 = this.getClass().getClassLoader().getResource("/footer.png").toExternalForm();
    Image image2 = Image.getInstance(new URL(imageUrl2));
    image2.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
    image2.setAlignment(Image.MIDDLE);

    float x2 = (PageSize.A4.getWidth() - image2.getScaledWidth()) / 2;
    float y2 = (PageSize.A4.getHeight() - image2.getScaledHeight()) / 2;
    image2.setAbsolutePosition(0, 0 );
    pdf.add(image2);

  }



  @PostConstruct
  public void initData() {
    catchData();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<DTVaccinationActView> getListDtActView() {
    return listDtActView;
  }

  public void setListDtActView(List<DTVaccinationActView> listDtActView) {
    this.listDtActView = listDtActView;
  }

  public List<DTVaccinationAct> getListDtVaccinationActs() {
    return listDtVaccinationActs;
  }

  public void setListDtVaccinationActs(List<DTVaccinationAct> listDtVaccinationActs) {
    this.listDtVaccinationActs = listDtVaccinationActs;
  }

  public List<DTVaccinationAct> getSelectedVaccinationActs() {
    return selectedVaccinationActs;
  }

  public void setSelectedVaccinationActs(List<DTVaccinationAct> selectedVaccinationActs) {
    this.selectedVaccinationActs = selectedVaccinationActs;
  }

  public List<DTVaccinationAct> getFilteredVaccinationActs() {
    return filteredVaccinationActs;
  }

  public void setFilteredVaccinationActs(List<DTVaccinationAct> filteredVaccinationActs) {
    this.filteredVaccinationActs = filteredVaccinationActs;
  }

  public Exporter<DataTable> getTextExporter() {
    return textExporter;
  }

  public void setTextExporter(Exporter<DataTable> textExporter) {
    this.textExporter = textExporter;
  }

  public HorizontalBarChartModel getHbarModel() {
    return hbarModel;
  }

  public void setHbarModel(HorizontalBarChartModel hbarModel) {
    this.hbarModel = hbarModel;
  }

  public PieChartModel getPieModel() {
    return pieModel;
  }

  private void catchData() {
    try {
      listDtVaccinationActs = iVaccinationActController.listVaccinationActs();
      createHorizontalBarModel();
      createPieModel();
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public void itemSelect(ItemSelectEvent event) {
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected",
            "Item Index: " + event.getItemIndex() + ", DataSet Index:" + event.getDataSetIndex());

    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public List<DTVaccinationAct> getVaccinationActs() {
    return listDtVaccinationActs;
  }


  public List<DTVaccinationActView> getReports(){
    return iVaccinationActController.getVaccinationActsView();
  }

  public DTVaccinationAct getVaccineActById(Long id) {
    try {
      return iVaccinationActController.getVaccinationActById(id);
    } catch (Exception e) {
      return new DTVaccinationAct();
    }
  }

  public void createHorizontalBarModel() {
    hbarModel = new HorizontalBarChartModel();
    ChartData data = new ChartData();

    HorizontalBarChartDataSet hbarDataSet = new HorizontalBarChartDataSet();
    hbarDataSet.setLabel("Datos");

    List<Number> values = getDiseaseQuantity();
    hbarDataSet.setData(values);

    List<String> bgColor = new ArrayList<>();
    bgColor.add("rgba(255, 99, 132, 0.2)");
    bgColor.add("rgba(153, 102, 255, 0.2)");
    bgColor.add("rgba(255, 159, 64, 0.2)");
    bgColor.add("rgba(255, 205, 86, 0.2)");
    bgColor.add("rgba(75, 192, 192, 0.2)");
    bgColor.add("rgba(54, 162, 235, 0.2)");
    bgColor.add("rgba(201, 203, 207, 0.2)");
    hbarDataSet.setBackgroundColor(bgColor);

    List<String> borderColor = new ArrayList<>();
    borderColor.add("rgb(255, 99, 132)");
    borderColor.add("rgb(153, 102, 255)");
    borderColor.add("rgb(255, 159, 64)");
    borderColor.add("rgb(255, 205, 86)");
    borderColor.add("rgb(75, 192, 192)");
    borderColor.add("rgb(54, 162, 235)");
    borderColor.add("rgb(201, 203, 207)");
    hbarDataSet.setBorderColor(borderColor);
    hbarDataSet.setBorderWidth(1);

    data.addChartDataSet(hbarDataSet);

    List<String> labels = getDiseaseLabels();
    data.setLabels(labels);

    hbarModel.setData(data);

    //Options
    BarChartOptions options = new BarChartOptions();
    CartesianScales cScales = new CartesianScales();
    CartesianLinearAxes linearAxes = new CartesianLinearAxes();
    linearAxes.setOffset(true);
    CartesianLinearTicks ticks = new CartesianLinearTicks();
    ticks.setBeginAtZero(true);
    linearAxes.setTicks(ticks);
    cScales.addXAxesData(linearAxes);
    options.setScales(cScales);

    Title title = new Title();
    title.setDisplay(true);
    title.setText("Cantidad de actos por enfermedad");
    options.setTitle(title);

    hbarModel.setOptions(options);
  }

  public List<String> getDiseaseLabels(){
    Map<String, String> mapString = new HashMap();
    if(getReports() != null) {
      for (DTVaccinationActView dtViewAct : getReports()) {
        mapString.putIfAbsent(dtViewAct.getDisease().toLowerCase(), dtViewAct.getDisease());
      }
    }
    List<String> finalList = new ArrayList<String>(mapString.values());
    return finalList;
  }

  public List<Number> getDiseaseQuantity(){
    List<Number> finalList = new ArrayList<>();
    List<String> diseaseList = getDiseaseLabels();
    Integer[] qtyArry = new Integer[diseaseList.size()];
    Integer i = 0;
    if(diseaseList != null){
      for(String disName : diseaseList){
        qtyArry[i] = 0;
        for(DTVaccinationActView dtViewAct : getReports()){
          if(disName.equalsIgnoreCase(dtViewAct.getDisease()))
            qtyArry[i]= qtyArry[i] +1;
        }
        i = i + 1;
      }
      for(Integer integ : qtyArry){
        finalList.add(integ);
      }
    }
    return finalList;
  }

  private void createPieModel() throws ParseException {
    pieModel = new PieChartModel();
    ChartData data = new ChartData();

    PieChartDataSet dataSet = new PieChartDataSet();
    List<Number> values = getAgeQuantity();
//    values.add(300);
//    values.add(50);
//    values.add(100);
    dataSet.setData(values);

    List<String> bgColors = new ArrayList<>();
    bgColors.add("rgba(153, 102, 255, 0.2)");
    bgColors.add("rgba(255, 159, 64, 0.2)");
    bgColors.add("rgba(54, 162, 235, 0.2)");
    dataSet.setBackgroundColor(bgColors);

    List<String> borderColor = new ArrayList<>();
    borderColor.add("rgb(153, 102, 255)");
    borderColor.add("rgb(255, 159, 64)");
    borderColor.add("rgb(54, 162, 235)");
    dataSet.setBorderColor(borderColor);

    data.addChartDataSet(dataSet);
    List<String> labels = new ArrayList<>();
    labels.add("0-17");
    labels.add("18-49");
    labels.add("50+");
    data.setLabels(labels);

    pieModel.setData(data);

    //Options
    PieChartOptions options = new PieChartOptions();

    Title title = new Title();
    title.setDisplay(true);
    title.setText("Cantidad de actos por edades");
    options.setTitle(title);

    pieModel.setOptions(options);
  }

  public List<Number> getAgeQuantity() throws ParseException {
    List<Number> finalList = new ArrayList<>();
    Integer[] qtyArry = new Integer[3];
    Date today = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    qtyArry[0] = 0;
    qtyArry[1] = 0;
    qtyArry[2] = 0;
    if(getReports() != null) {
      for (DTVaccinationActView dtActView : getReports()) {
        Date birth = sdf.parse(dtActView.getPersonBirthday());
        long diffInTime = today.getTime() - birth.getTime();
        long diffInYears = (diffInTime / (1000l * 60 * 60 * 24 * 365));
        if (diffInYears < 18)
          qtyArry[0] = qtyArry[0] + 1;
        if (diffInYears >= 18 && diffInYears < 50)
          qtyArry[1] = qtyArry[1] + 1;
        if (diffInYears >= 50)
          qtyArry[2] = qtyArry[2] + 1;
      }
    }
    for(Integer integ : qtyArry){
      finalList.add(integ);
    }
    return finalList;
  }

}
