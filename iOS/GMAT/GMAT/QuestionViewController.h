//
//  QuestionViewController.h
//  GMAT
//
//  Created by Trung Đức on 3/14/16.
//  Copyright © 2016 Trung Đức. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "QuestionPack.h"

@interface QuestionViewController : UIViewController<UITableViewDataSource, UITableViewDelegate>


@property (nonatomic, strong) QuestionPack *questionPack;
@property (nonatomic, strong) NSMutableArray *questions;
@property (weak, nonatomic) IBOutlet UITableView *tbvQuestion;
@property (weak, nonatomic) IBOutlet UILabel *lblResult;

@end
