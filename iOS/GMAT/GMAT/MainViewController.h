//
//  MainViewController.h
//  GMAT
//
//  Created by Trung Đức on 4/6/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "BaseViewController.h"

@interface MainViewController : BaseViewController <UITableViewDelegate, UITableViewDataSource>

@property (weak, nonatomic) IBOutlet UITableView *tbvQuestionPacks;
@property (nonatomic, strong) NSMutableArray *questionPacks;

+ (MainViewController *)sharedInstance;

@end
