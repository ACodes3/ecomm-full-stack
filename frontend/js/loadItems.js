import { createApp } from "https://unpkg.com/vue@3/dist/vue.esm-browser.js";

createApp({
  data() {
    return {
      items: [],
      sellers: [],
      regions: [],
      selectedItem: 1,
      selectedRegion: "",
      item: {
        name: "",
        price: null,
        brand: "",
        image: null,
        created_at: "",
        seller: {
          id: 1,
        },
      },
      seller: {
        name: "",
        company: "",
        email: "",
        phone: "",
        region: {
          id: 1,
        },
      },
      region: {
        name: "",
      },
      //pagination definitions, start page and how many items per page
      currentPage: 1,
      itemsPerPage: 8,
      //error and information handling message
      message: "",
    };
  },
  created() {
    this.loadItems();
    this.loadSellers();
    this.loadRegions();
    this.loadItemById(1);
  },
  computed: {
    //pagination methods
    totalPages() {
      return Math.ceil(this.items.length / this.itemsPerPage);
    },
    paginatedItems() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.items.slice().reverse().slice(startIndex, endIndex);
    },
  },
  methods: {
    //LOADING DATA FROM API
    //ITEMS
    loadItems() {
      axios
        .get("http://localhost:8080/items/getItems")
        .then((response) => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch((error) => console.error(error));
    },
    // Load a single item by ID and populate the item object
    loadItemById(itemId) {
      axios
        .get(`http://localhost:8080/items/getItem/${itemId}`)
        .then((response) => {
          this.selectedItem = response.data;
        })
        .catch((error) => {
          console.error(error);
        });
    },
    //SELLERS
    loadSellers() {
      axios
        .get("http://localhost:8080/sellers/getSellers")
        .then((response) => {
          this.sellers = response.data;
          console.log(this.sellers);
        })
        .catch((error) => console.error(error));
    },
    //REGIONS
    loadRegions() {
      axios
        .get("http://localhost:8080/regions/getRegions")
        .then((response) => {
          this.regions = response.data;
          console.log(this.regions);
        })
        .catch((error) => console.error(error));
    },

    //SELLERS BY REGION ID
    loadSellersByRegion(regionId) {
      axios
       .get(`http://localhost:8080/sellers/getSellerByRegionId/${regionId}`)
       .then((response) => {
          this.sellers = response.data;
          console.log(this.sellers);
        })
       .catch((error) => console.error(error));
    },

    filterSellers() {
      if (this.selectedRegion) {
        this.loadSellersByRegion(this.selectedRegion);
      } else {
        this.loadSellers();
      }
    },
  
    showAllSellers() {
      this.selectedRegion = "";
      this.loadSellers();
    },

    // ADDING DATA TO THE API
    //ITEMS
    handleFileUpload(event) {
      this.item.image = event.target.files[0];
    },

    postItem() {
      let currentDate = new Date();
      let formattedDate =
        currentDate.getFullYear() +
        "-" +
        ("0" + (currentDate.getMonth() + 1)).slice(-2) +
        "-" +
        ("0" + currentDate.getDate()).slice(-2);

      // Assign formatted date to item's created_at property
      this.item.created_at = formattedDate;

      // Create FormData object
      let formData = new FormData();
      formData.append("id", this.item.id);
      formData.append("name", this.item.name);
      formData.append("price", this.item.price);
      formData.append("brand", this.item.brand);
      formData.append("created_at", this.item.created_at);
      formData.append("file", this.item.image);
      formData.append("seller.id", this.item.seller.id);

      // Make axios post request
      axios
        .post("http://localhost:8080/items/createItem", formData, {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        })
        .then((response) => {
          // Handle success response
          this.item.id = "";
          this.item.name = "";
          this.item.brand = "";
          this.item.created_at = "";
          this.item.image = "";
          this.item.price = "";
          this.item.seller.id = "";
          this.loadItems();
        })
        .catch((error) => {
          // Handle error
          console.error(error);
        });
    },
    //REGIONS
    postRegion() {
      axios
        .post("http://localhost:8080/regions/createRegion", this.region)
        .then((response) => {
          this.loadRegions();
        })
        .catch((error) => {
          console.error(error);
        });
    },
    //SELLERS
    postSeller() {
      axios
        .post("http://localhost:8080/sellers/createSeller", this.seller)
        .then((response) => {
          this.seller.id = "";
          this.seller.name = "";
          this.seller.email = "";
          this.seller.name = "";
          this.seller.phone = "";
          this.seller.region.id = 1;
          this.loadSellers();
        })
        .catch((error) => {
          console.error(error);
        });
    },

    // UPDATING DATA IN THE API
    // Update Region
    updateRegion() {
      axios
        .put(`http://localhost:8080/regions/updateRegion`, this.region)
        .then((response) => {
          this.message = "Region updated successfully";
          this.loadRegions();
        })
        .catch((error) => {
          console.error(error);
          this.message = "Failed to update region";
        });
    },
    //UPDATE SELLER
    updateSeller() {
      axios
        .put(
          `http://localhost:8080/sellers/updateSeller/${this.seller.id}`,
          this.seller
        )
        .then((response) => {
          this.message = "Seller updated successfully";
          this.loadSellers();
        })
        .catch((error) => {
          console.error("Error updating seller:", error);
          this.message = "Failed to update seller";
        });
    },

    //UPDATING DATA IN THE API FOR ITEMS
    updateItem() {
      axios
        .put(
          `http://localhost:8080/items/updateItem/${this.item.id}`,
          this.item
        )
        .then((response) => {
          this.message = "Item updated successfully";
          this.loadItems();
        })
        .catch((error) => {
          console.error("Error updating item:", error);
          this.message = "Failed to update item";
        });
    },

    // DELETING DATA FROM THE API
    //REGION
    deleteRegion(regionId) {
      console.log(regionId);

      axios
        .delete("http://localhost:8080/regions/deleteRegion/" + regionId)
        .then((response) => {
          console.log(response.data);
          this.message = "Region deleted successfully";
          this.loadRegions();
        })
        .catch((error) => {
          console.error("Error deleting region:", error);
          this.message = "Failed to delete region";
        });
    },
    //SELLER
    deleteSeller(sellerId) {
      console.log(sellerId);

      axios
        .delete("http://localhost:8080/sellers/deleteSeller/" + sellerId)
        .then((response) => {
          console.log(response.data);
          this.message = "Seller deleted successfully";
          this.loadSellers();
        })
        .catch((error) => {
          console.error("Error deleting seller:", error);
          this.message = "Failed to delete seller";
        });
    },
    //ITEM
    deleteItem(itemId) {
      console.log(itemId);

      axios
        .delete("http://localhost:8080/items/deleteItem/" + itemId)
        .then((response) => {
          console.log(response.data);
          this.message = "Item deleted successfully";
          this.loadItems();
        })
        .catch((error) => {
          console.error("Error deleting item:", error);
          this.message = "Failed to delete item";
        });
    },
    // FETCH SELLER DATA FOR SELECTED SELLER ID
    fetchSellerData(sellerId) {
      const selectedSeller = this.sellers.find(
        (seller) => seller.id === sellerId
      );
      if (selectedSeller) {
        this.seller = { ...selectedSeller };
      }
    },

    // FETCH SELLER DATA FOR SELECTED ITEM ID
    fetchItemData(itemId) {
      const selectedItemId = this.items.find(
        (item) => item.id === itemId
      );
      if (selectedItemId) {
        this.item = { ...selectedItemId };
      }
    },

    //pagination arrow click event functions
    changePage(pageNumber) {
      this.currentPage = pageNumber;
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--;
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++;
      }
    },
  },
}).mount("#app");
