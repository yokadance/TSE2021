package backoffice.model;

public enum SortOrder {
  ASCENDING,
  DESCENDING,
  UNSORTED;

  public boolean isAscending() {
    return ASCENDING.equals(this);
  }
}
