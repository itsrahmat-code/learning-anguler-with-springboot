import { ChangeDetectorRef, Component, Inject, OnInit, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { ChartData, ChartOptions } from 'chart.js';
import DataLabelsPlugin from 'chartjs-plugin-datalabels';
import { InvoiceService } from '../../service/sale-product/invoice.service';
import { ExpenseService } from '../../service/Expanses/expense.service';
import { DuelistService } from '../../service/sale-product/duelist.service';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.html',
  styleUrls: ['./dashboard.css']
})
export class Dashboard implements OnInit {


  // Sales
  todaysSlaes!: number;
  lastWeekSales!: number;
  lastMonthSales!: number;

  salesPeriod = 'Today';
  salesValue: number = 0;
  periods = ['Today', 'Last Week', 'Last Month'];

  // Dues
  todaysDue!: number;
  lastMonthDue!: number;

  duePeriod = 'Today';
  dueValue: number = 0;
  duePeriods = ['Today', 'Last Month'];  // <-- use this in HTML

  // Expense
  expenseAmount: number = 0;

  // Platform
  isBrowser: boolean;

  // Date labels
  currentYear = new Date().getFullYear();
  currentMonthName = '';
  lastMonthName = '';

  // Chart setup
  profitChartLabels: string[] = [];
  profitChartData: ChartData<'line'> = {
    labels: [],
    datasets: [
      {
        label: 'Monthly Profit',
        data: [],
        borderColor: 'darkviolet',
        backgroundColor: 'rgba(208, 140, 237, 0.29)',
        fill: true,
        tension: 0.0,
        pointBackgroundColor: '#48ffffff',
        pointBorderColor: '#794a04ff',
        pointRadius: 3,
        pointHoverRadius: 10
      }
    ]
  };

  profitChartOptions: ChartOptions = {
    responsive: true,
    plugins: {
      legend: { display: true },
      tooltip: {
        callbacks: {
          label: context => `Profit: ৳${context.raw}`
        }
      }
    },
    scales: {
      y: {
        beginAtZero: true,
        title: { display: true, text: 'Profit (৳)' }
      },
      x: {
        title: { display: true, text: 'Month' }
      }
    }
  };

  // Info sections
  infoSections = [
    {
      title: 'Stock Management',
      items: ['Current Stock: 4,230 units', 'Low Stock Alerts: 7 items', 'Out-of-Stock Items: 2', 'Fast-moving: Accessories']
    },
    {
      title: 'Profit & Cost Analytics',
      items: ['Gross Profit: $18,900', 'COGS: $6,400', 'Margin: 67%']
    },
    {
      title: 'Sales Management',
      items: ['Sales Today: 48', 'Pending: 12, Completed: 36', 'Cancelled: 2', 'Avg Fulfillment Time: 1.2 Days']
    },
    {
      title: 'Time-Based Trends',
      items: ['Monthly Sales Growth: +12%', 'Seasonal Peak: Nov-Dec']
    },
    {
      title: 'Category Performance',
      items: ['Top Category: Laptops', 'Profit Leader: Accessories', 'Low Stock: Accessories']
    },
    {
      title: 'Alerts & Notifications',
      bgClass: 'text-white bg-danger',
      items: ['Low Stock: 7 items', 'High Returns Detected: keyboards', 'Sales Drop: Asus (↓15%)']
    }
  ];

  constructor(
    @Inject(PLATFORM_ID) private platformId: Object,
    private invoiceService: InvoiceService,
    private expenseService: ExpenseService,
    private dueService: DuelistService,
    private cdr: ChangeDetectorRef
  ) {
    this.isBrowser = isPlatformBrowser(this.platformId);
  }

  ngOnInit(): void {
    this.setCurrentMonth();
    this.setLastMonth();

    this.getSalesSummary();
    this.getDueSummary();
    this.loadLastMonthExpenses();

    if (this.isBrowser) {
      this.generateProfitChartData();
    }
  }

  updateSales(period: string): void {
    this.salesPeriod = period;
    switch (period) {
      case 'Today':
        this.salesValue = this.todaysSlaes;
        break;
      case 'Last Week':
        this.salesValue = this.lastWeekSales;
        break;
      case 'Last Month':
        this.salesValue = this.lastMonthSales;
        break;
    }
  }

  updateDue(period: string): void {
    this.duePeriod = period;
    switch (period) {
      case 'Today':
        this.dueValue = this.todaysDue;
        break;
      case 'Last Month':
        this.dueValue = this.lastMonthDue;
        break;
    }
  }

  getSalesSummary(): void {
    this.invoiceService.getSalesSummary().subscribe({
      next: data => {
        this.todaysSlaes = data.today;
        this.lastWeekSales = data.last7Days;
        this.lastMonthSales = data.last30Days;
        this.updateSales(this.salesPeriod); // Apply default
      },
      error: err => console.error(err)
    });
  }

  getDueSummary(): void {
    this.dueService.getDueSummary().subscribe({
      next: data => {
        this.todaysDue = data.today;
        this.lastMonthDue = data.last30Days;
        this.updateDue(this.duePeriod); // Apply default
      },
      error: err => console.error(err)
    });
  }

 loadLastMonthExpenses(): void {
  this.expenseService.getLastMonthExpenses().subscribe({
    next: response => {
      const rawAmount = response.expenseAmount || '0';
      const numericAmount = Number(rawAmount.toString().replace(/[^\d.-]/g, ''));
      this.expenseAmount = isNaN(numericAmount) ? 0 : numericAmount;

      this.cdr.markForCheck();
    },
    error: err => {
      console.error('Error fetching expenses:', err);
      this.expenseAmount = 0;
      this.cdr.markForCheck();
    }
  });
}

  setCurrentMonth(): void {
    const monthNames = [
      'January', 'February', 'March', 'April', 'May', 'June',
      'July', 'August', 'September', 'October', 'November', 'December'
    ];
    this.currentMonthName = monthNames[new Date().getMonth()];
  }

  setLastMonth(): void {
    const monthNames = [
      'January', 'February', 'March', 'April', 'May', 'June',
      'July', 'August', 'September', 'October', 'November', 'December'
    ];
    const now = new Date();
    const lastMonthIndex = now.getMonth() === 0 ? 11 : now.getMonth() - 1;
    this.lastMonthName = monthNames[lastMonthIndex];
  }

  generateProfitChartData(): void {
    const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep'];
    const profits = months.map(() => Math.floor(Math.random() * 10000) + 1000);

    this.profitChartLabels = months;
    this.profitChartData.labels = months;
    this.profitChartData.datasets[0].data = profits;
  }
}
