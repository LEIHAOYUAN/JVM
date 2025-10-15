package com.lei.jvm.stu.string;

import org.apache.commons.lang3.StringUtils;

public class SplitAddressLineHelper {
    public String formattedAddressLine1;
    public String formattedAddressLine1SplitP1;
    public String formattedAddressLine1SplitP2;
    public String formattedAddressLine2;

    public String formattedFullAddress;

    public SplitAddressLineHelper(String fullAddress, String address2) {
        String[] addressLines = fullAddress.split(",", 2);
        if (addressLines.length == 0 || addressLines.length == 1) {
            formattedAddressLine1 = fullAddress;
            formattedAddressLine2 = "";
        } else {
            formattedAddressLine1 = addressLines[0].trim();
            String addressLine2 = addressLines[1].trim();
            int commaIndex = addressLine2.lastIndexOf(',');
            int spaceIndex = addressLine2.lastIndexOf(' ');
            if (commaIndex > -1 && spaceIndex > -1 && commaIndex == spaceIndex - 1) {
                addressLine2 = new StringBuilder(addressLine2).deleteCharAt(commaIndex).toString();
            }
            formattedAddressLine2 = addressLine2;
        }
        formattedAddressLine1SplitP1 = formattedAddressLine1;
        formattedAddressLine1SplitP2 = "";
        if (StringUtils.isNotBlank(address2)) {
            formattedAddressLine1SplitP2 = address2.trim();
            formattedAddressLine1 = formattedAddressLine1 + " " + formattedAddressLine1SplitP2;
        }
        formattedFullAddress = formattedAddressLine1;
        if (StringUtils.isNotBlank(formattedAddressLine2)) {
            formattedFullAddress = formattedFullAddress + ", " + formattedAddressLine2;
        }
    }
}
