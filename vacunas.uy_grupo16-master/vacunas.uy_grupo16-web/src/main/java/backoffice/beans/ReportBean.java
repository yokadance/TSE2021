package backoffice.beans;

import static backoffice.util.Utils.addDetailMessage;
import static com.github.adminfaces.template.util.Assert.has;

import DTO.*;
import IController.*;
import backoffice.model.Filter;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.omnifaces.util.Faces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.export.Exporter;
import org.primefaces.component.export.PDFOptions;

@Named("ReportBean")
@ViewScoped
public class ReportBean implements Serializable {

  private static final long serialVersionUID = 1L;

  private ObjectMapper mapper = new ObjectMapper();

  private Long id;

  private String vacName;
  private String disName;

  private List<DTBatch> listDtBatches;

  private List<DTVaccine> listDtVaccines;

  private List<DTVaccine> selectedVaccines;

  private List<DTVaccine> filteredVaccines;

  private Exporter<DataTable> textExporter;

  private PDFOptions pdfOpt;

  Filter<DTVaccine> filter = new Filter<>(new DTVaccine());

  @EJB
  IVaccineController iVaccineController;

  @EJB
  IBatchController iBatchController;

  @EJB
  IPackageController iPackageController;

  public ReportBean() {}

  public void init() {
    customizationOptions();
    if (Faces.isAjaxRequest()) {
      return;
    }
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

  public String getVacName() {
    return vacName;
  }

  public void setVacName(String vacName) {
    this.vacName = vacName;
  }

  public String getDisName() {
    return disName;
  }

  public void setDisName(String disName) {
    this.disName = disName;
  }

  public List<DTBatch> getListDtBatches() {
    return listDtBatches;
  }

  public void setListDtBatches(List<DTBatch> listDtBatches) {
    this.listDtBatches = listDtBatches;
  }

  public List<DTVaccine> getListDtVaccines() {
    return listDtVaccines;
  }

  public void setListDtVaccines(List<DTVaccine> listDtVaccines) {
    this.listDtVaccines = listDtVaccines;
  }

  public List<DTVaccine> getSelectedVaccines() {
    return selectedVaccines;
  }

  public void setSelectedVaccines(List<DTVaccine> selectedVaccines) {
    this.selectedVaccines = selectedVaccines;
  }

  public List<DTVaccine> getFilteredVaccines() {
    return filteredVaccines;
  }

  public void setFilteredVaccines(List<DTVaccine> filteredVaccines) {
    this.filteredVaccines = filteredVaccines;
  }

  public Filter<DTVaccine> getFilter() {
    return filter;
  }

  public void setFilter(Filter<DTVaccine> filter) {
    this.filter = filter;
  }

  public Exporter<DataTable> getTextExporter() {
    return textExporter;
  }

  public void setTextExporter(Exporter<DataTable> textExporter) {
    this.textExporter = textExporter;
  }

  public PDFOptions getPdfOpt() {
    return pdfOpt;
  }

  public void setPdfOpt(PDFOptions pdfOpt) {
    this.pdfOpt = pdfOpt;
  }

  private void catchData() {
    try {
      listDtVaccines = iVaccineController.listVaccines();
      listDtBatches = iBatchController.getBatchesByVaccineId(getId());
    } catch (Exception e) {
      //            return new ArrayList<>();
    }
  }

  public List<DTVaccine> getVaccines() {
    return listDtVaccines;
  }

  public List<DTPackage> getPackages() {
    List<DTBatch> listBatch = iBatchController.getBatchesByVaccineId(getId());
    List<DTPackage> listPackFinal = new ArrayList<>();
    for (DTBatch dtb : listBatch) {
      List<DTPackage> listPack = iPackageController.getPackagesByBatchId(dtb.getId());
      for (DTPackage dtp : listPack) {
        listPackFinal.add(dtp);
      }
    }
    return listPackFinal;
  }

  public List<DTVaccineReport> getReports() {
    return iVaccineController.getVaccineReport(getId());
  }

  public DTVaccine getVaccineById(Long id) {
    try {
      return iVaccineController.getVaccineById(id);
    } catch (Exception e) {
      return new DTVaccine();
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

    String imageUrl = this.getClass().getClassLoader().getResource("/logo2.png").toExternalForm();
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
    float[] columnWidths = {1.7f, 1.8f, 0.8f, 0.8f, 1f, 0.8f, 1f};
    table.setWidths(columnWidths);

    PdfPCell cell1 = new PdfPCell(new Paragraph("Plan"));
    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell2 = new PdfPCell(new Paragraph("Centro"));
    cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell3 = new PdfPCell(new Paragraph("Nº Lote"));
    cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell4 = new PdfPCell(new Paragraph("Cant. en Lote"));
    cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell5 = new PdfPCell(new Paragraph("Nº Paquete"));
    cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell6 = new PdfPCell(new Paragraph("Cant. en Paquete"));
    cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);

    PdfPCell cell7 = new PdfPCell(new Paragraph("Estado"));
    cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);

    table.addCell(cell1);
    table.addCell(cell2);
    table.addCell(cell3);
    table.addCell(cell4);
    table.addCell(cell5);
    table.addCell(cell6);
    table.addCell(cell7);


    List<DTVaccineReport> listVacRep = getReports();
    if(listVacRep != null){
      for (DTVaccineReport dtActRep : listVacRep) {

        PdfPCell cellD1 = new PdfPCell(new Paragraph(dtActRep.getPlanName()));
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cellD1);

        PdfPCell cellD2 = new PdfPCell(new Paragraph(dtActRep.getCenterName()));
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cellD2);

        PdfPCell cellD3 = new PdfPCell(new Paragraph(dtActRep.getBatchNumber().toString()));
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cellD3);

        PdfPCell cellD4 = new PdfPCell(new Paragraph(String.valueOf(dtActRep.getBatchAvailable())));
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cellD4);

        PdfPCell cellD5 = new PdfPCell(new Paragraph(dtActRep.getpNumber().toString()));
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cellD5);

        PdfPCell cellD6 = new PdfPCell(new Paragraph(dtActRep.getpQuantity().toString()));
        cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cellD6);

        String state = dtActRep.getpState();
        PdfPCell cellD7 = new PdfPCell(new Paragraph(state));
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


}
